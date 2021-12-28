package com.jsoftgroup.exception;

import brave.Tracing;
import com.jsoftgroup.exception.response.APIError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER  = LoggerFactory.getLogger(AppExceptionHandler.class);

    @Autowired
	private Tracing tracing;

    /*@Autowired
    EmailService emailService;*/


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String errMsg = ex.getParameterName() + " parameter is missing";

        APIError error = new APIError("Your input request is invalid", errMsg);

        error.setTraceId(tracing.tracer().currentSpan().context().traceIdString());
        error.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        error.setStatus(HttpStatus.BAD_REQUEST);

        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String errMsg = ex.getName() + " should be of type " + ex.getRequiredType().getName();

        APIError error = new APIError("Your input request is invalid", errMsg);

        error.setTraceId(tracing.tracer().currentSpan().context().traceIdString());
        error.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        error.setStatus(HttpStatus.BAD_REQUEST);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ AccessDeniedException.class ,InvalidBearerTokenException.class, AuthenticationException.class })
    public ResponseEntity<Object> handleAuthenticationException(Exception ex, WebRequest request) {

        APIError error = new APIError("Unauthorized", ex.getMessage());

        error.setTraceId(tracing.tracer().currentSpan().context().traceIdString());
        error.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        error.setStatus(HttpStatus.UNAUTHORIZED);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, error, headers, HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler({ BusinessException.class })
    protected ResponseEntity<Object> handleBadRequest(BusinessException e, WebRequest request) {
    	APIError error = new APIError("Business Validation", e.getMessage());
    	
    	error.setTraceId(tracing.tracer().currentSpan().context().traceIdString());
    	error.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setErrorCode(e.getErrorCode());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternalError(Exception e,WebRequest request) {
        LOGGER.info("==> Application Error <==",e);

        APIError error = new APIError("Internal Server Error", e.getMessage());

        error.setTraceId(tracing.tracer().currentSpan().context().traceIdString());
        error.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        /*if (emailService.triggerEmail()) {
            triggerErrorEmail(e);
        }*/

        return handleExceptionInternal(e, error, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleGenericNotFoundException(NotFoundException e,WebRequest request) {
        APIError error = new APIError("Resource Not Found", e.getMessage());

        error.setTraceId(tracing.tracer().currentSpan().context().traceIdString());
        error.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());
        error.setStatus(HttpStatus.NOT_FOUND);
        error.setErrorCode(e.getErrorCode());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error, headers, HttpStatus.NOT_FOUND, request);
    }

    /*public void triggerErrorEmail(Exception exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        List<String> stackList;
            try {
                EmailDto mail=new EmailDto();
                mail.setTemplateLocation("email-template");
                mail.setSubject("Application Internal Error");

                Map model = new HashMap();

                model.put("traceId", tracing.tracer().currentSpan().context().traceIdString());
                model.put("ErrMessage", exception.getMessage());
                model.put("ErrCause", NestedExceptionUtils.getRootCause(exception));

                exception.printStackTrace(pw);
                stackList = Arrays.asList(sw.toString().split("\\r?\\n"));
                model.put("ErrStack", stackList);

                mail.setModel(model);

                emailService.sendEmailMessage(mail);

            } catch (Exception emailException) {
                LOGGER.error("==> Error Sending Email notification <==",emailException);
            }
    }*/
}

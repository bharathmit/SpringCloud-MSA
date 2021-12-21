package com.jsoftgroup.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ApiLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        final long start = System.nanoTime();

        if(httpReq.getRequestURI().equalsIgnoreCase("/health")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/actuator/health");
            dispatcher.forward(request, response);
        } else {
            chain.doFilter(request,response);
        }
        final long end = System.nanoTime();
        log.info(buildMessage(httpReq, httpRes,end - start));
    }

    private String buildMessage(final HttpServletRequest request,final HttpServletResponse response, final long executionTime) {
        final StringBuilder buffer = new StringBuilder();
        buffer.append("method=").append(request.getMethod());
        buffer.append(" uri=").append(request.getRequestURI());

        if(request.getQueryString() != null ) {
            buffer.append('?').append(request.getQueryString());
        }

        buffer.append(" executionTime=").append(executionTime);
        buffer.append(" status code=").append(response.getStatus());
        return buffer.toString();
    }


}


package com.jsoftgroup.exception.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class APIError {
	
	@Getter	@Setter	
	private HttpStatus status;
	@Getter	@Setter
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;
	@Getter	@Setter
	private String errorCode="EXXX";
	@Getter	@Setter	
	private String errorMessage;
	@Getter	@Setter	
	private String description;
	@Getter	@Setter	
	private String path;
	@Getter	@Setter	
	private String traceId;
	
	public APIError(String description,String errorMessage) {
		this.description = description;
        this.errorMessage = errorMessage;
        this.timestamp=new Date();
    }

	public APIError() {
	}

}

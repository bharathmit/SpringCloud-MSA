package com.jsoftgroup.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private String errorCode;

	public BusinessException(String errorCode,String message) {
	    super(message);
        this.errorCode=errorCode;
    }
}

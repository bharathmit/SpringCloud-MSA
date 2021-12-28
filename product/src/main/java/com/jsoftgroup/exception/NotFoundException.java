package com.jsoftgroup.exception;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String errorCode;
    public NotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode=errorCode;
    }
}

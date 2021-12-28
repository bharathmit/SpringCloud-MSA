package com.jsoftgroup.exception.response;

import lombok.Getter;

public enum ErrorDescription {

	SERVER_ERROR("E-001","Application Server Error. Please try again later.")
    ;
	
	@Getter
    private String message;

    @Getter
    private String code;

    private ErrorDescription(String code, String message) {
        this.code = code;
        this.message = message;
    }

}

package com.kelvin.oocl.springsecurityjwt.common;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private String errorMessage;
    private HttpStatus httpStatus;
    private String status;

    private ErrorResponse(String status, String errorMessage, HttpStatus httpStatus) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public static ErrorResponse of(String status, String message, HttpStatus httpStatus){
        return new ErrorResponse(status, message, httpStatus);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

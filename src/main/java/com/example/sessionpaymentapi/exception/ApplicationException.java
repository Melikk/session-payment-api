package com.example.sessionpaymentapi.exception;

import org.springframework.http.HttpStatus;


public abstract class ApplicationException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public ApplicationException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getCode() {
        return status.value();
    }

    @Override
    public String getMessage() {
        return message;
    }
}

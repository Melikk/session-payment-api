package com.example.sessionpaymentapi.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Requested resource was not found");
    }
}

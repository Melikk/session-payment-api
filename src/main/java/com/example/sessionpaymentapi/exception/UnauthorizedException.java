package com.example.sessionpaymentapi.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApplicationException {
    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, "You are not authorized to access this resource");
    }
}

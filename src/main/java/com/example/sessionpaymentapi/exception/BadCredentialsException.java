package com.example.sessionpaymentapi.exception;

import org.springframework.http.HttpStatus;

public class BadCredentialsException extends ApplicationException {
    public BadCredentialsException() {
        super(HttpStatus.UNAUTHORIZED, "Invalid username or password");
    }
}

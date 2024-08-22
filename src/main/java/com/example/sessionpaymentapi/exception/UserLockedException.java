package com.example.sessionpaymentapi.exception;

import org.springframework.http.HttpStatus;

public class UserLockedException extends ApplicationException {
    public UserLockedException() {
        super(HttpStatus.FORBIDDEN, "User account is locked. Please try again later.");
    }
}

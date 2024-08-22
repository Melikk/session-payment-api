package com.example.sessionpaymentapi.exception;

import org.springframework.http.HttpStatus;

public class InsufficientFundsException extends ApplicationException {
    public InsufficientFundsException() {
        super(HttpStatus.BAD_REQUEST, "Insufficient funds for the transaction");
    }
}
package com.example.sessionpaymentapi.exception;

import org.springframework.http.HttpStatus;

public class TransactionFailureException extends ApplicationException {
    public TransactionFailureException() {
        super(HttpStatus.CONFLICT, "A transaction-related error occurred, please try again later.");
    }
}

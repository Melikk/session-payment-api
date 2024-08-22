package com.example.sessionpaymentapi.exception.handler;

import com.example.sessionpaymentapi.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<ErrorResponse> handleBaseServiceException(ApplicationException exception) {
        log.error(exception.getMessage(), exception);

        return ResponseEntity
                .status(exception.getStatus())
                .body(ErrorResponse.builder()
                        .errorCode(exception.getCode())
                        .message(exception.getMessage())
                        .build()
                );
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ValidateErrorResponse> handleValidException(MethodArgumentNotValidException exception) {
        Map<String, String> fields = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = null;

            if (error instanceof FieldError fieldError) {
                fieldName = fieldError.getField();
            } else {
                fieldName = error.getObjectName();
            }

            fields.put(fieldName, error.getDefaultMessage());
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ValidateErrorResponse.builder()
                        .errorCode(HttpStatus.BAD_REQUEST.value())
                        .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .fields(fields)
                        .build());

    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleNotReadableException(HttpMessageNotReadableException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .errorCode(HttpStatus.BAD_REQUEST.value())
                        .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .build());
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorResponse> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ErrorResponse.builder()
                        .errorCode(HttpStatus.METHOD_NOT_ALLOWED.value())
                        .message(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
                        .build());
    }


    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ErrorResponse> excludeBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.builder()
                        .errorCode(HttpStatus.UNAUTHORIZED.value())
                        .message(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<ErrorResponse> excludeNoResourceFoundException(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .errorCode(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler({LockedException.class})
    public ResponseEntity<ErrorResponse> handleLockedException(LockedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse.builder()
                        .errorCode(HttpStatus.FORBIDDEN.value())
                        .message("User account is locked. Please try again later.")
                        .build());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .build());
    }
}

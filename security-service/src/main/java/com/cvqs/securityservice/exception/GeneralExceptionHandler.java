package com.cvqs.securityservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for handling custom exceptions.
 */
@RestControllerAdvice
public class GeneralExceptionHandler {

    /**
     * Exception handler for UserNotFoundException.
     *
     * @param exception The UserNotFoundException.
     * @return ResponseEntity with the exception message and HTTP status code.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handle(UserNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}

package com.cvqs.usermanagementservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Global exception handler for handling custom exceptions in the application.
 */
@RestControllerAdvice
public class GeneralExceptionHandler {

    /**
     * Handles the UserNotFoundException and returns a ResponseEntity with the appropriate HTTP status code.
     *
     * @param exception the UserNotFoundException to handle
     * @return a ResponseEntity with the error message and HTTP status code
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handle(UserNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}

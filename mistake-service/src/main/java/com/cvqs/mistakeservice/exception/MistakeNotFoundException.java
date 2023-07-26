package com.cvqs.mistakeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class representing a Mistake not found error.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MistakeNotFoundException extends RuntimeException {

    /**
     * Constructs a new MistakeNotFoundException with the specified error message.
     *
     * @param message the error message
     */
    public MistakeNotFoundException(String message) {
        super(message);
    }
}

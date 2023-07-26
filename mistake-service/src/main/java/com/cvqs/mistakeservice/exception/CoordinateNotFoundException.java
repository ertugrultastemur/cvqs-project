package com.cvqs.mistakeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class representing a Coordinate not found error.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CoordinateNotFoundException extends RuntimeException {

    /**
     * Constructs a new CoordinateNotFoundException with the specified error message.
     *
     * @param message the error message
     */
    public CoordinateNotFoundException(String message) {
        super(message);
    }
}

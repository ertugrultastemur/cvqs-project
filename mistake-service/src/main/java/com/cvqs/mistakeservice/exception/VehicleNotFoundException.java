package com.cvqs.mistakeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class representing a Vehicle not found error.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class VehicleNotFoundException extends RuntimeException {

    /**
     * Constructs a new VehicleNotFoundException with the specified error message.
     *
     * @param message the error message
     */
    public VehicleNotFoundException(String message) {
        super(message);
    }
}

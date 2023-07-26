package com.cvqs.mistakeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class representing an SQL error related to Mistake operations.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MistakeSQLException extends RuntimeException {

    /**
     * Constructs a new MistakeSQLException with the specified error message.
     *
     * @param message the error message
     */
    public MistakeSQLException(String message) {
        super(message);
    }
}

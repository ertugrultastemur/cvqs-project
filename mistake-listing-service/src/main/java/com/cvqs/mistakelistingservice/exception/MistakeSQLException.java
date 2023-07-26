package com.cvqs.mistakelistingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown when a mistake related to SQL occurs.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MistakeSQLException extends RuntimeException {
    private ExceptionMessage exceptionMessage;

    /**
     * Constructs a new MistakeSQLException with the specified error message.
     *
     * @param message the error message
     */
    public MistakeSQLException(String message) {
        super(message);
    }

    /**
     * Constructs a new MistakeSQLException with the specified exception message.
     *
     * @param message the exception message
     */
    public MistakeSQLException(ExceptionMessage message) {
        this.exceptionMessage = message;
    }

    /**
     * Constructs a new MistakeSQLException with the specified error message and exception message.
     *
     * @param message          the error message
     * @param exceptionMessage the exception message
     */
    public MistakeSQLException(String message, ExceptionMessage exceptionMessage) {
        super(message);
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * Returns the exception message associated with this MistakeSQLException.
     *
     * @return the exception message
     */
    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }
}

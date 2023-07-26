package com.cvqs.mistakelistingservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown when an I/O error occurs related to mistakes.
 */
public class MistakeIOException extends RuntimeException {
    private ExceptionMessage exceptionMessage;

    /**
     * Constructs a new MistakeIOException with the specified error message.
     *
     * @param message the error message
     */
    public MistakeIOException(String message) {
        super(message);
    }

    /**
     * Constructs a new MistakeIOException with the specified exception message.
     *
     * @param message the exception message
     */
    public MistakeIOException(ExceptionMessage message) {
        this.exceptionMessage = message;
    }

    /**
     * Constructs a new MistakeIOException with the specified error message and exception message.
     *
     * @param message          the error message
     * @param exceptionMessage the exception message
     */
    public MistakeIOException(String message, ExceptionMessage exceptionMessage) {
        super(message);
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * Returns the exception message associated with this MistakeIOException.
     *
     * @return the exception message
     */
    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }
}

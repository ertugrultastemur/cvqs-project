package com.cvqs.mistakelistingservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown when a mistake is not found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MistakeNotFoundException extends RuntimeException {

    Logger logger = LoggerFactory.getLogger(MistakeNotFoundException.class);
    private ExceptionMessage exceptionMessage;

    /**
     * Constructs a new MistakeNotFoundException with the specified error message.
     *
     * @param message the error message
     */
    public MistakeNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new MistakeNotFoundException with the specified exception message.
     *
     * @param message the exception message
     */
    public MistakeNotFoundException(ExceptionMessage message) {
        this.exceptionMessage = message;
    }

    /**
     * Constructs a new MistakeNotFoundException with the specified error message and exception message.
     *
     * @param message          the error message
     * @param exceptionMessage the exception message
     */
    public MistakeNotFoundException(String message, ExceptionMessage exceptionMessage) {
        super(message);
        logger.info("MistakeNotFoundException: ctor entered");
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * Returns the exception message associated with this MistakeNotFoundException.
     *
     * @return the exception message
     */
    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }

}

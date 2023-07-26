package com.cvqs.mistakelistingservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown when a vehicle is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class VehicleNotFoundException extends RuntimeException {
    private ExceptionMessage exceptionMessage;

    Logger logger = LoggerFactory.getLogger(VehicleNotFoundException.class);

    /**
     * Constructs a new VehicleNotFoundException with the specified error message.
     *
     * @param message the error message
     */
    public VehicleNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new VehicleNotFoundException with the specified exception message.
     *
     * @param message the exception message
     */
    public VehicleNotFoundException(ExceptionMessage message) {
        this.exceptionMessage = message;
    }

    /**
     * Constructs a new VehicleNotFoundException with the specified error message and exception message.
     *
     * @param message          the error message
     * @param exceptionMessage the exception message
     */
    public VehicleNotFoundException(String message, ExceptionMessage exceptionMessage) {
        super(message);
        logger.info("VehicleNotFoundException: ctor entered.");
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * Returns the exception message associated with this VehicleNotFoundException.
     *
     * @return the exception message
     */
    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }
}

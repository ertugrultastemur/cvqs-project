package com.cvqs.securityservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a user is not found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Constructs a new UserNotFoundException with the specified error message.
     *
     * @param message the error message
     */
    public UserNotFoundException(String message) {
        super(message);
        logger.error("User could not be found: " + message);
    }
}

package com.cvqs.usermanagementservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for when a user is not found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Constructs a UserNotFoundException with the specified error message.
     *
     * @param message the error message
     */
    public UserNotFoundException(String message){
        super(message);
        logger.error("User could not be found: " + message);
    }
}

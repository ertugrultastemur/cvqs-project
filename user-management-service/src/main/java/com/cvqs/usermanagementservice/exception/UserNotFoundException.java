package com.cvqs.usermanagementservice.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    public UserNotFoundException(String e){
        super(e);
        logger.error("User could not found: " + e);

    }
}

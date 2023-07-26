package com.cvqs.mistakeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class representing an IOException related to image handling.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ImageIOException extends java.io.IOException {

    /**
     * Constructs a new ImageIOException with the specified error message.
     *
     * @param message the error message
     */
    public ImageIOException(String message) {
        super(message);
    }

}

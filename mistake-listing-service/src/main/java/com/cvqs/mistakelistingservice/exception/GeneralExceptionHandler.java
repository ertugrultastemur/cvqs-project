package com.cvqs.mistakelistingservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/**
 * This class provides global exception handling for the application.
 * It handles specific types of exceptions and returns appropriate HTTP responses.
 */
@RestControllerAdvice
public class GeneralExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    /**
     * Handles VehicleNotFoundException and returns an appropriate HTTP response.
     *
     * @param exception the VehicleNotFoundException to handle
     * @return the ResponseEntity containing the exception message and HTTP status
     */
    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handle(VehicleNotFoundException exception) {
        logger.trace("GeneralExceptionHandler: VehicleNotFoundException handled.");
        return new ResponseEntity<>(exception.getExceptionMessage(), Objects.requireNonNull(HttpStatus.resolve(exception.getExceptionMessage().status())));
    }

    /**
     * Handles MistakeNotFoundException and returns an appropriate HTTP response.
     *
     * @param exception the MistakeNotFoundException to handle
     * @return the ResponseEntity containing the exception message and HTTP status
     */
    @ExceptionHandler(MistakeNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handle(MistakeNotFoundException exception) {
        logger.trace("GeneralExceptionHandler: MistakeNotFoundException handled.");
        return new ResponseEntity<>(exception.getExceptionMessage(), HttpStatus.resolve(exception.getExceptionMessage().status()));
    }

    /**
     * Handles IOException and returns an appropriate HTTP response.
     *
     * @param exception the IOException to handle
     * @return the ResponseEntity with the corresponding HTTP status
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ExceptionMessage> handle(IOException exception) {
        logger.trace("GeneralExceptionHandler: IOException handled.");
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(status);
    }

    /**
     * Handles SQLException and returns an appropriate HTTP response.
     *
     * @param exception the SQLException to handle
     * @return the ResponseEntity with the corresponding HTTP status
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionMessage> handle(SQLException exception) {
        logger.trace("GeneralExceptionHandler: SQLException handled.");
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(status);
    }
}

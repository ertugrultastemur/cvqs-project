package com.cvqs.mistakeservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Global exception handler for handling different types of exceptions.
 */
@RestControllerAdvice
public class GeneralExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    /**
     * Exception handler for MistakeNotFoundException.
     *
     * @param exception the MistakeNotFoundException
     * @return a ResponseEntity with the appropriate HTTP status and error message
     */
    @ExceptionHandler(MistakeNotFoundException.class)
    public ResponseEntity<?> handle(MistakeNotFoundException exception){
        logger.info("MistakeNotFoundException handled.");
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for CoordinateNotFoundException.
     *
     * @param exception the CoordinateNotFoundException
     * @return a ResponseEntity with the appropriate HTTP status and error message
     */
    @ExceptionHandler(CoordinateNotFoundException.class)
    public ResponseEntity<?> handle(CoordinateNotFoundException exception){
        logger.info("CoordinateNotFoundException handled.");
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for IOException.
     *
     * @param exception the IOException
     * @return a ResponseEntity with the appropriate HTTP status and error message
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handle(ImageIOException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception handler for SQLException.
     *
     * @param exception the SQLException
     * @return a ResponseEntity with the appropriate HTTP status and error message
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handle(MistakeSQLException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception handler for VehicleNotFoundException.
     *
     * @param exception the VehicleNotFoundException
     * @return a ResponseEntity with the appropriate HTTP status and error message
     */
    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<?> handle(VehicleNotFoundException exception){
        logger.info("VehicleNotFoundException handled.");
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}

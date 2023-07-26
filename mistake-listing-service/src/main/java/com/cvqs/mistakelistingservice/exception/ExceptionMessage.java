/**
 * This class represents an exception message containing information about an exception.
 */
package com.cvqs.mistakelistingservice.exception;


/**
 * Constructs a new ExceptionMessage with the provided values.
 *
 * @param timestamp the timestamp of the exception
 * @param status    the HTTP status code of the exception
 * @param error     the error message
 * @param message   the detailed message of the exception
 * @param path      the path of the request that triggered the exception
 */
public record ExceptionMessage (String timestamp,
                                int status,
                                String error,
                                String message,
                                String path){

}

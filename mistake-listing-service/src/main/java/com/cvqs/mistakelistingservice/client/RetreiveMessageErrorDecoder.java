/**
 * This class implements the ErrorDecoder interface to handle error responses
 * from the Mistake Listing Service API. It decodes the response and throws
 * custom exceptions based on the status code.
 */
package com.cvqs.mistakelistingservice.client;

import com.cvqs.mistakelistingservice.exception.*;
import com.google.common.annotations.Beta;
import com.google.common.io.CharStreams;
import feign.Response;
import feign.codec.ErrorDecoder;
import io.micrometer.core.instrument.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class RetreiveMessageErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    Logger logger = LoggerFactory.getLogger(RetreiveMessageErrorDecoder.class);

    /**
     * Decodes the error response and throws custom exceptions based on the status code.
     *
     * @param methodKey the method key
     * @param response  the response from the API
     * @return an Exception object representing the decoded error
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        logger.info("RetreiveMessageErrorDecoder: decode method entered.");
        ExceptionMessage message = null;
        try (InputStream body = response.body().asInputStream()) {
            message = new ExceptionMessage((String) response.headers().get("date").toArray()[0],
                    response.status(),
                    HttpStatus.resolve(response.status()).getReasonPhrase(),
                    IOUtils.toString(body, StandardCharsets.UTF_8),
                    response.request().url());
            logger.info("RetreiveMessageErrorDecoder: message created.");

        } catch (IOException exception) {
            logger.info("RetreiveMessageErrorDecoder: message creating failed. " + exception.getMessage());
            return new Exception(exception.getMessage());
        }
        switch (response.status()) {
            case 404: {
                logger.info("RetreiveMessageErrorDecoder: 404 error: " + message);
                throw new MistakeNotFoundException(message);
            }
            case 403: {
                logger.info("RetreiveMessageErrorDecoder: 403 error: " + message);
                throw new MistakeIOException(message);
            }
            case 500: {
                logger.info("RetreiveMessageErrorDecoder: 500 error: " + message);
                throw new MistakeSQLException(message);
            }
            default:
                logger.info("RetreiveMessageErrorDecoder: error: " + message);
                return errorDecoder.decode(methodKey, response);
        }
    }
}

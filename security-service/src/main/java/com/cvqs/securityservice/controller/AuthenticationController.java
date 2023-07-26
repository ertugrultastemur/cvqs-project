
package com.cvqs.securityservice.controller;

import com.cvqs.securityservice.authentication.AuthenticationRequest;
import com.cvqs.securityservice.authentication.AuthenticationResponse;
import com.cvqs.securityservice.authentication.CreateUserRequestDto;
import com.cvqs.securityservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

/**
 * Controller class for authentication-related endpoints.
 */
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;

    /**
     * Endpoint for user registration.
     *
     * @param request The CreateUserRequestDto containing user registration details.
     * @return ResponseEntity containing the AuthenticationResponse.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody CreateUserRequestDto request){
        logger.info("AuthenticationController: register endpoint entered");
        return ResponseEntity.ok(authenticationService.register(request));
    }

    /**
     * Endpoint for user authentication.
     *
     * @param request The AuthenticationRequest containing user authentication credentials.
     * @return ResponseEntity containing the AuthenticationResponse.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        logger.info("AuthenticationController: authenticate endpoint entered");
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    /**
     * Endpoint for validating a token.
     *
     * @param token The token to validate.
     * @return ResponseEntity with a success message.
     */
    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        logger.info("AuthenticationController: validateToken endpoint entered");
        authenticationService.validateToken(token);
        logger.info("AuthenticationController: Token validated");
        return ResponseEntity.ok("Token is valid");
    }
}


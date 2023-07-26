/**
 * RouteValidator component for validating secured routes in the gateway.
 */
package com.example.gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    public static final List<String> openApiEndpoints = List.of(
            "/v1/auth/register",
            "/v1/auth/authenticate",
            "/v1/auth/validate",
            "/eureka"
    );

    /**
     * Predicate to determine if a request is for a secured route.
     */
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}

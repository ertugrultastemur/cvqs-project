/**
 * Filter component for authentication handling in the gateway.
 */
package com.example.gateway.filter;

import com.example.gateway.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    private final RouteValidator validator;

    private final RestTemplate template;
    private final JwtUtil jwtUtil;


    public AuthenticationFilter(RouteValidator validator, JwtUtil jwtUtil, RestTemplate template) {
        super(AuthenticationFilter.Config.class);
        logger.info("AuthenticationFilter: Entered constructor.");
        this.validator = validator;
        this.jwtUtil = jwtUtil;
        this.template = template;
    }

    /**
     * Applies the authentication filter to the incoming request.
     *
     * @param config the configuration object for the filter
     * @return the GatewayFilter instance
     */
    @Override
    public GatewayFilter apply(Config config) {
        logger.info("AuthenticationFilter: Entered apply method.");
        return (exchange, chain) -> {
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (token == null || !token.startsWith("Bearer ")) {
                logger.error("AuthenticationFilter: Missing or invalid Authorization header.");
                throw new RuntimeException("Missing or invalid Authorization header");
            }

            String authToken = token.substring(7);

            try {
                template.getForObject("http://localhost:9090/v1/auth/validate?token=" + authToken, String.class);
            } catch (Exception e) {
                System.out.println("invalid access...!"+e.getMessage());
                logger.error("AuthenticationFilter: Unauthorized access to application."+ e.getMessage());
                throw new RuntimeException("Unauthorized access to application");
            }
            logger.info("AuthenticationFilter: Token valid.");
            return chain.filter(exchange);
        };
    }

    /**
     * Configuration class for the authentication filter.
     */
    public static class Config {
        private List<String> roles;

        /**
         * Retrieves the list of roles.
         *
         * @return the list of roles
         */
        public List<String> getRoles() {
            return roles;
        }

        /**
         * Sets the list of roles.
         *
         * @param roles the list of roles
         */
        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }
}

/**
 * JwtUtil class for JWT (JSON Web Token) operations.
 */
package com.example.gateway.util;

import com.example.gateway.filter.AuthenticationFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "33527944527a4459703966584d6a4d7847757542487868587653676e74636755";

    Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * Validates the provided token.
     *
     * @param token the JWT token to validate
     */
    public void validateToken(final String token) {
        logger.info("JwtUtil: validateToken method entered.");
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        logger.info("JwtUtil: validateToken method closed.");
    }

    /**
     * Retrieves the signing key for JWT verification.
     *
     * @return the signing key as a cryptographic key
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        logger.info("JwtUtil: getSignKey method entered.");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

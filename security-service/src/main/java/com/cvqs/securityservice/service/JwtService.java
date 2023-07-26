package com.cvqs.securityservice.service;

import com.cvqs.securityservice.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service class for JWT token generation, validation, and extraction.
 */
@Service
public class JwtService {

    private final String SECRET_KEY = "33527944527a4459703966584d6a4d7847757542487868587653676e74636755";
    private final long jwtExpiration = 3000000;

    /**
     * Extracts a specific claim from the provided token.
     *
     * @param token          The token from which to extract the claim.
     * @param claimsResolver The function to resolve the claim from the token's claims.
     * @param <T>            The type of the claim.
     * @return The resolved claim value.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts the username from the provided token.
     *
     * @param token The token from which to extract the username.
     * @return The username.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the roles from the provided token.
     *
     * @param token The token from which to extract the roles.
     * @return The list of roles.
     */
    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        Object role = claims.get("role");

        if (role instanceof List) {
            List<?> roleList = (List<?>) role;

            if (!roleList.isEmpty() && roleList.get(0) instanceof Role) {
                return roleList.stream()
                        .map(r -> ((Role) r).toString())
                        .collect(Collectors.toList());
            }
        }

        return Collections.emptyList();
    }

    /**
     * Generates a JWT token with the provided extra claims and user details.
     *
     * @param extraClaims   The extra claims to include in the token.
     * @param userDetails  The user details for whom to generate the token.
     * @return The generated JWT token.
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    /**
     * Generates a JWT token with the provided user details.
     *
     * @param userDetails The user details for whom to generate the token.
     * @return The generated JWT token.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Checks if the provided token is valid for the given user details and roles.
     *
     * @param token        The token to validate.
     * @param userDetails The user details for whom to validate the token.
     * @param roles        The list of roles associated with the token.
     * @return True if the token is valid, false otherwise.
     */
    public boolean isTokenValid(String token, UserDetails userDetails, List<String> roles) {
        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername())) &&
                (userDetails.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> roles.contains(grantedAuthority.getAuthority()))) &&
                !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        extraClaims.put("role", role);
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

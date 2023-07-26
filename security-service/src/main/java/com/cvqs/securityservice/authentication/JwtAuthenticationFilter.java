
package com.cvqs.securityservice.authentication;


import com.cvqs.securityservice.repository.TokenRepository;
import com.cvqs.securityservice.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * JWT authentication filter that intercepts incoming requests and performs JWT authentication.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);


    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        logger.info("JwtAuthenticationFilter: doFilterInternal method entered.");
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        final List<String> roles;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            logger.info("JwtAuthenticationFilter: filtered.");
            return;
        }
        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);
        roles = jwtService.extractRoles(jwt);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.info("JwtAuthenticationFilter: Authentication started.");
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            if (jwtService.isTokenValid(jwt, userDetails,roles) && isTokenValid) {
                logger.info("JwtAuthenticationFilter: Token valid.");
                boolean hasAccess = false;
                for (String role : roles) {
                    if (userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role))) {
                        logger.info("JwtAuthenticationFilter: hasAccess = True.");
                        hasAccess = true;
                        break;
                    }
                }
                if (hasAccess) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)

                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    logger.info("JwtAuthenticationFilter: Token authenticated.");
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
package com.cvqs.securityservice.service;

import com.cvqs.securityservice.authentication.AuthenticationRequest;
import com.cvqs.securityservice.authentication.AuthenticationResponse;
import com.cvqs.securityservice.authentication.CreateUserRequestDto;
import com.cvqs.securityservice.model.User;
import com.cvqs.securityservice.model.token.Token;
import com.cvqs.securityservice.model.token.TokenType;
import com.cvqs.securityservice.repository.TokenRepository;
import com.cvqs.securityservice.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing user authentication and registration.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Registers a new user with the provided information.
     *
     * @param request The request object containing user registration data.
     * @return The authentication response containing the access token.
     */
    public AuthenticationResponse register(CreateUserRequestDto request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(request.getRole())
                .build();

        User savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

    /**
     * Authenticates a user with the provided credentials.
     *
     * @param request The request object containing user authentication data.
     * @return The authentication response containing the access token.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        var user = userRepository.findUserByUsername(request.getUsername()).orElseThrow(()-> new UsernameNotFoundException("User could not found by name: " + request.getUsername()));
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user,jwtToken);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

    /**
     * Validates the provided access token.
     *
     * @param token The access token to validate.
     */
    public void validateToken(String token){
        List<String> roles = jwtService.extractRoles(token);
        UserDetails userDetails = userRepository.findUserByUsername(jwtService.extractUsername(token)).orElseThrow();
        jwtService.isTokenValid(token,userDetails,roles);
    }

    /**
     * Saves a new token for the specified user.
     *
     * @param user     The user for whom to save the token.
     * @param jwtToken The JWT token to save.
     */
    public void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    /**
     * Revokes all tokens associated with the specified user.
     *
     * @param user The user for whom to revoke tokens.
     */
    public void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}

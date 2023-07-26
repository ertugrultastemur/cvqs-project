package com.cvqs.securityservice.repository;

import com.cvqs.securityservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing users.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user.
     * @return An optional containing the user if found, or an empty optional.
     */
    Optional<User> findUserByUsername(String username);

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user.
     * @return An optional containing the user if found, or an empty optional.
     */
    Optional<User> findByUsername(String username);
}

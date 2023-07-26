package com.cvqs.usermanagementservice.repository;

import com.cvqs.usermanagementservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The UserRepository is an interface responsible for user database operations.
 */
public interface UserManagementRepository extends JpaRepository<User,Integer> {

    /**
     * Finds a user by their username.
     *
     * @param userName the username of the user
     * @return an optional containing the user, if found
     */
    Optional<User> findByUsername(String userName);

    /**
     * Checks if a user with the specified username exists.
     *
     * @param userName the username to check
     * @return true if the user exists, false otherwise
     */
    Boolean existsByUsername(String userName);
}

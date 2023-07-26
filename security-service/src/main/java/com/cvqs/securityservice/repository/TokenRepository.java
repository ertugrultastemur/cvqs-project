package com.cvqs.securityservice.repository;

import com.cvqs.securityservice.model.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing tokens.
 */
public interface TokenRepository extends JpaRepository<Token, Integer> {

    /**
     * Retrieves all valid tokens associated with a user.
     *
     * @param id The ID of the user.
     * @return A list of valid tokens.
     */
    @Query(value = """
            select t from Token t inner join User u
            on t.user.id = u.id
            where u.id = :id 
            and (t.expired = false or t.revoked = false)
            """)
    List<Token> findAllValidTokenByUser(Integer id);

    /**
     * Retrieves a token by its value.
     *
     * @param token The token value.
     * @return An optional containing the token if found, or an empty optional.
     */
    Optional<Token> findByToken(String token);
}


package com.cvqs.securityservice.authentication;

import com.cvqs.securityservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a request to create a user.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDto {

    private String email;

    private String username;

    private String password;

    private List<Role> role;
}


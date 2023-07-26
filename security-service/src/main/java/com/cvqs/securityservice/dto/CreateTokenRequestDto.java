
package com.cvqs.securityservice.dto;

import com.cvqs.securityservice.model.token.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for creating a token request.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTokenRequestDto {

    private String username;

    private Token token;
}
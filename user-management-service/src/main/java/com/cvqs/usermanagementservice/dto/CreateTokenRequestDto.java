package com.cvqs.usermanagementservice.dto;

import com.cvqs.usermanagementservice.model.token.Token;
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

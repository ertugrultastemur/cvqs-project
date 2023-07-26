package com.cvqs.usermanagementservice.dto;

import com.cvqs.usermanagementservice.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * DTO class for creating a user request.
 */
public class CreateUserRequestDto {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private String email;

    private String username;

    private String password;

    private List<Role> roles;


    public CreateUserRequestDto(String email, String username, String password,List<Role> roles) {
        logger.info("CreateRequestDto: ctor entered.");
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public CreateUserRequestDto() {
    }


    public String getEmail() {
        logger.info("CreateUserRequestDto: getEmail entered.");
        return email;
    }

    public void setEmail(String email) {
        logger.info("CreateUserRequestDto: setEmail entered.");

        this.email = email;
    }

    public String getUsername() {
        logger.info("CreateUserRequestDto: getUserName entered.");

        return username;
    }

    public void setUsername(String username) {
        logger.info("CreateUserRequestDto: setUserName entered.");
        this.username = username;
    }

    public String getPassword() {
        logger.info("CreateUserRequestDto: getPassword entered.");
        return password;
    }

    public void setPassword(String password) {
        logger.info("CreateUserRequestDto: setPassword entered.");
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
package com.cvqs.usermanagementservice.dto;

import com.cvqs.usermanagementservice.model.Role;

/**
 * DTO class for a role.
 */
public class RoleDto {

    private String username;

    private Role role;

    /**
     * Constructs a RoleDto object with the specified username and role.
     *
     * @param username the username
     * @param role the role
     */
    public RoleDto(String username, Role role){
        this.username = username;
        this.role = role;
    }

    /**
     * Returns the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }
}

package com.cvqs.securityservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents a permission that can be assigned to a role.
 */

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    OPERATOR_READ("operator:read"),
    OPERATOR_UPDATE("operator:update"),
    OPERATOR_CREATE("operator:create"),
    OPERATOR_DELETE("operator:delete");

    @Getter
    private final String permission;
}
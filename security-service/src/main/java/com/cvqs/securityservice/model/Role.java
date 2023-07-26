package com.cvqs.securityservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.cvqs.securityservice.model.Permission.*;


/**
 * Represents a role assigned to a user.
 */
@RequiredArgsConstructor
public enum Role {


    ADMIN(
            Set.of(
            ADMIN_READ,
            ADMIN_UPDATE,
            ADMIN_DELETE,
            ADMIN_CREATE
            )
  ),
    OPERATOR(
            Set.of(
            OPERATOR_READ,
            OPERATOR_UPDATE,
            OPERATOR_DELETE
            )
  )


    ;

    @Getter
    private final Set<Permission> permissions;

    /**
     * Returns the authorities associated with the role.
     *
     * @return The authorities associated with the role.
     */
    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}

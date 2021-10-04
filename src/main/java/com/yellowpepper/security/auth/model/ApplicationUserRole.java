package com.yellowpepper.security.auth.model;

import com.google.common.collect.Sets;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(ApplicationUserPermission.DEBT_CREATE, ApplicationUserPermission.DEBT_READ, ApplicationUserPermission.DEBT_UPDATE, ApplicationUserPermission.DEBT_DELETE)),
    ADMINTRAINEE(Sets.newHashSet(
        ApplicationUserPermission.DEBT_READ, ApplicationUserPermission.DEBT_UPDATE)),
    USER(Sets.newHashSet(ApplicationUserPermission.DEBT_READ));

    private final Set<ApplicationUserPermission> permissions;


    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}

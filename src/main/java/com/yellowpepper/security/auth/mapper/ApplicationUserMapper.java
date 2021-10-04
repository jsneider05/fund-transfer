package com.yellowpepper.security.auth.mapper;

import com.yellowpepper.security.auth.entity.PrivilegeEntity;
import com.yellowpepper.security.auth.entity.RoleEntity;
import com.yellowpepper.security.auth.entity.UserEntity;
import com.yellowpepper.security.auth.model.ApplicationUser;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUserMapper {

    public Optional<ApplicationUser> mapToApplicationUser(UserEntity user) {
        return Optional.of( new ApplicationUser(getPermissions(user.getRoles()),
                user.getPassword(),
                user.getUserName(),
                true,
                true,
                true,
                user.isEnabled()));
    }

    private Set<SimpleGrantedAuthority> getPermissions(Set<RoleEntity> roleEntities) {
        Set<SimpleGrantedAuthority> permissions = roleEntities.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet());

        roleEntities.forEach(
                role -> getRolePrivilegesName(role.getPrivileges())
                        .forEach(
                                privilegeName -> permissions.add(new SimpleGrantedAuthority(privilegeName))));
        return permissions;
    }

    private Set<String> getRolePrivilegesName(Set<PrivilegeEntity> privilegeEntities) {
        return privilegeEntities.stream()
                .map(PrivilegeEntity::getName)
                .collect(Collectors.toSet());
    }

}

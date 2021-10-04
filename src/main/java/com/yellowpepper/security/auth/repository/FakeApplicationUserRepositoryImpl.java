package com.yellowpepper.security.auth.repository;

import com.yellowpepper.security.auth.model.ApplicationUser;
import com.google.common.collect.Lists;
import com.yellowpepper.security.auth.model.ApplicationUserRole;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository("fakeApplicationUserRepository")
public class FakeApplicationUserRepositoryImpl implements ApplicationUserRepository {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserRepositoryImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        return Lists.newArrayList(
                new ApplicationUser(
                        ApplicationUserRole.ADMIN.getSimpleGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "Joan",
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        ApplicationUserRole.USER.getSimpleGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "user",
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        ApplicationUserRole.ADMINTRAINEE.getSimpleGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "Alex",
                        true,
                        true,
                        true,
                        true
                )
        );
    }
}

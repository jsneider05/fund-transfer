package com.yellowpepper.security.auth.repository;

import com.yellowpepper.security.auth.model.ApplicationUser;
import java.util.Optional;

public interface ApplicationUserRepository {

    Optional<ApplicationUser> selectApplicationUserByUsername (String username);

}

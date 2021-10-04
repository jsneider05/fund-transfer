package com.yellowpepper.security.auth.repository;

import com.yellowpepper.security.auth.mapper.ApplicationUserMapper;
import com.yellowpepper.security.auth.model.ApplicationUser;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository("postgresApplicationUserRepository")
public class ApplicationUserRepositoryImpl implements ApplicationUserRepository {

  private final ApplicationUserJpaRepository applicationUserJpaRepository;
  private final ApplicationUserMapper applicationUserMapper;

  @Override
  public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
    return this.applicationUserMapper.mapToApplicationUser(
        this.applicationUserJpaRepository.selectApplicationUserByUsername(username));
  }
}

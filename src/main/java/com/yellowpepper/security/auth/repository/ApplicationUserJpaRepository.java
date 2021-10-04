package com.yellowpepper.security.auth.repository;

import com.yellowpepper.security.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationUserJpaRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT i FROM UserEntity i WHERE i.userName = :userName")
    UserEntity selectApplicationUserByUsername(@Param("userName") String userName);
}

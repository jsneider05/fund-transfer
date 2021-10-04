package com.yellowpepper.fundtransfer.infrastructure.configuration.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {
    "com.yellowpepper.fundtransfer.infrastructure.entity",
    "com.yellowpepper.security.auth.entity"
})
@EnableJpaRepositories(basePackages = {
    "com.yellowpepper.fundtransfer.infrastructure.adapter",
    "com.yellowpepper.security.auth.repository"
})
public class JpaConfig {

}

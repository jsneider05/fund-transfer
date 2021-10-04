package com.yellowpepper.fundtransfer.infrastructure.adapter.repository;

import com.yellowpepper.fundtransfer.domain.port.repository.AccountRepository;
import com.yellowpepper.fundtransfer.infrastructure.adapter.AccountJpaRepository;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class AccountRepositoryPostgres implements AccountRepository {

  private final AccountJpaRepository jpaRepository;

  @Override
  public Integer updateFundById(UUID id, BigDecimal fund) {
    return jpaRepository.updateFundById(id, fund);
  }


}

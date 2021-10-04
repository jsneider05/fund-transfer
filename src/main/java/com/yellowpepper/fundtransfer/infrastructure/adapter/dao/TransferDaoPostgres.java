package com.yellowpepper.fundtransfer.infrastructure.adapter.dao;

import com.yellowpepper.fundtransfer.domain.port.dao.TransferDao;
import com.yellowpepper.fundtransfer.infrastructure.adapter.TransferJpaRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class TransferDaoPostgres implements TransferDao {

  private final TransferJpaRepository jpaRepository;

  @Override
  public Long countAllByOriginAccountNumberAndCreatedAt(Long originAccountNumber,
      LocalDateTime createdAt) {
    return jpaRepository.countAllByAccount_AccountNumberAndCreatedAtIsAfterAndSuccessfulIsTrue(
        originAccountNumber, createdAt);
  }

//  @Override
//  public Transfer findByAccountNumber(Long originAccountNumber) {
//    return jpaRepository.findByOriginAccountNumber(originAccountNumber);
//  }
}

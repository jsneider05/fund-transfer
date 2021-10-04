package com.yellowpepper.fundtransfer.infrastructure.adapter;

import com.yellowpepper.fundtransfer.infrastructure.entity.TransferEntity;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferJpaRepository extends JpaRepository<TransferEntity, UUID> {

  TransferEntity findByAccount_AccountNumber(Long accountNumber);

  Long countAllByAccount_IdAndCreatedAtIsAfterAndSuccessfulIsTrue(UUID accountId,
      LocalDateTime createdAt);

  Long countAllByAccount_AccountNumberAndCreatedAtIsAfterAndSuccessfulIsTrue(Long originAccountNumber,
      LocalDateTime createdAt);

}

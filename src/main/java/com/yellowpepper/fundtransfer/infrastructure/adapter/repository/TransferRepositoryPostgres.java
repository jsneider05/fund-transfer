package com.yellowpepper.fundtransfer.infrastructure.adapter.repository;

import com.yellowpepper.fundtransfer.domain.model.Transfer;
import com.yellowpepper.fundtransfer.domain.port.repository.TransferRepository;
import com.yellowpepper.fundtransfer.infrastructure.adapter.TransferJpaRepository;
import com.yellowpepper.fundtransfer.infrastructure.adapter.mapper.TransferMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class TransferRepositoryPostgres implements TransferRepository {

  private final TransferJpaRepository jpaRepository;
  private final TransferMapper mapper;

  @Override
  public UUID create(Transfer transfer) {
    return jpaRepository.save(mapper.toEntity(transfer)).getId();
  }
}

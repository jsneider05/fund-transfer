package com.yellowpepper.fundtransfer.infrastructure.adapter.mapper;

import com.yellowpepper.fundtransfer.domain.model.Transfer;
import com.yellowpepper.fundtransfer.infrastructure.entity.TransferEntity;
import java.util.UUID;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TransferMapper {

  private final AccountMapper accountMapper;

  public final Transfer toTransfer(TransferEntity entity) {
    return new Transfer(
        entity.getId(),
//          entity.getAccount().getAccountNumber(),
        entity.getDestinationAccountNumber(),
        entity.getAmount(),
        entity.getCurrency(),
        entity.getTaxRate(),
        entity.getConversionRate(),
        entity.isSuccessful(),
        entity.getCreatedAt(),
        entity.getDescription(),
        accountMapper.toAccount(entity.getAccount())
    );
  }

  public final TransferEntity toEntity(Transfer transfer) {
    return new TransferEntity(
        UUID.randomUUID(),
        transfer.getDestinationAccountNumber(),
        transfer.getAmount(),
        transfer.getCurrency(),
        transfer.getTaxRate(),
        transfer.getConversionRate(),
        transfer.isSuccessful(),
        transfer.getCreatedAt(),
        transfer.getDescription(),
        accountMapper.toEntity(transfer.getAccount())
    );
  }

}

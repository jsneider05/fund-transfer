package com.yellowpepper.fundtransfer.infrastructure.adapter.mapper;

import com.yellowpepper.fundtransfer.domain.model.Account;
import com.yellowpepper.fundtransfer.infrastructure.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

  public final Account toAccount(AccountEntity entity) {
    return new Account(
        entity.getId(),
        entity.getAccountNumber(),
        entity.getTotalFund(),
        entity.getCurrency(),
        entity.isEnabled(),
        entity.getCreatedAt(),
        entity.getUser().getId()
    );
  }

  public final AccountEntity toEntity(Account account) {
    return AccountEntity.builder()
        .id(account.getId())
        .accountNumber(account.getAccountNumber())
        .totalFund(account.getTotalFund())
        .currency(account.getCurrency())
        .enabled(account.isEnabled())
        .createdAt(account.getCreatedAt())
        .build();
  }
}

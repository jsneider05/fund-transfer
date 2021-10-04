package com.yellowpepper.fundtransfer.infrastructure.adapter.dao;

import com.yellowpepper.fundtransfer.domain.exception.AccountFundException;
import com.yellowpepper.fundtransfer.domain.model.Account;
import com.yellowpepper.fundtransfer.domain.port.dao.AccountDao;
import com.yellowpepper.fundtransfer.infrastructure.adapter.AccountJpaRepository;
import com.yellowpepper.fundtransfer.infrastructure.adapter.mapper.AccountMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class AccountDaoPostgres implements AccountDao {

  private static final String ACCOUNT_NOT_FOUND_LOG = "[account not found][accountNumber: {}]";
  private static final String ACCOUNT_NOT_FOUND = "[account not found][accountNumber: {}]";

  private final AccountJpaRepository jpaRepository;
  private final AccountMapper mapper;

  @Override
  public Account findByAccountNumber(Long accountNumber) {
    return mapper.toAccount((jpaRepository.findByAccountNumber(accountNumber))
        .orElseThrow(() -> {
          log.error(ACCOUNT_NOT_FOUND_LOG, accountNumber);
          throw new AccountFundException(ACCOUNT_NOT_FOUND);
        }));
  }
}

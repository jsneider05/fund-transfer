package com.yellowpepper.fundtransfer.domain.service;

import com.yellowpepper.fundtransfer.application.request.TransferRequest;
import com.yellowpepper.fundtransfer.application.response.TransferResponse;
import com.yellowpepper.fundtransfer.domain.exception.AccountFundException;
import com.yellowpepper.fundtransfer.domain.exception.TransferLimitException;
import com.yellowpepper.fundtransfer.domain.model.Account;
import com.yellowpepper.fundtransfer.domain.model.Transfer;
import com.yellowpepper.fundtransfer.domain.port.dao.AccountDao;
import com.yellowpepper.fundtransfer.domain.port.dao.TransferDao;
import com.yellowpepper.fundtransfer.domain.port.repository.AccountRepository;
import com.yellowpepper.fundtransfer.domain.port.repository.TransferRepository;
import com.yellowpepper.fundtransfer.domain.validator.AccountValidator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateTransferService {

  private static final int TRANSFER_LIMIT_PER_DAY = 3;
  private static final BigDecimal LIMIT_NEW_RATE = BigDecimal.valueOf(100.0);
  private static final BigDecimal CAD_RATE = BigDecimal.valueOf(1.26);
  private static final BigDecimal TAX_RATE_FIVE = BigDecimal.valueOf(0.05);
  private static final BigDecimal TAX_RATE_TWO = BigDecimal.valueOf(0.02);
  private static final String INSUFFICIENT_FUNDS_ORIGIN_ACCOUNT = "[insufficient funds][origin_account: {}]";
  private static final String INSUFFICIENT_FUNDS = "insufficient funds";
  private static final String TRANSFERS_PER_DAY_EXCEEDED = "[transfers per day exceeded]";

  private final TransferDao transferDao;
  private final TransferRepository transferRepository;
  private final AccountDao accountDao;
  private final AccountRepository accountRepository;

  public TransferResponse execute(TransferRequest transferRequest) {

    Account originAccount = accountDao.findByAccountNumber(transferRequest.getOriginAccountNumber());

    AccountValidator.hasEnoughFund(transferRequest.getAmount())
        .validate(originAccount)
        .throwIfInvalid(() -> {
          log.error(INSUFFICIENT_FUNDS_ORIGIN_ACCOUNT, transferRequest.getOriginAccountNumber());
          throw new AccountFundException(INSUFFICIENT_FUNDS);
        });

    Long transfersToday = transferDao.countAllByOriginAccountNumberAndCreatedAt(
        transferRequest.getOriginAccountNumber(),
        LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT)
    );

    AccountValidator.hasTransferPerDay(transfersToday >= TRANSFER_LIMIT_PER_DAY)
        .validate(originAccount)
        .throwIfInvalid(() -> {
          log.error(TRANSFERS_PER_DAY_EXCEEDED);
          throw new TransferLimitException(TRANSFERS_PER_DAY_EXCEEDED);
        });

    BigDecimal taxCollected = computeTax(transferRequest.getAmount());

    BigDecimal newOriginFund = computeNewOriginFound(originAccount.getTotalFund(), transferRequest.getAmount(), taxCollected);

    originAccount.setTotalFund(newOriginFund);

    Account destinationAccount = accountDao.findByAccountNumber(transferRequest.getDestinationAccountNumber());

    destinationAccount.setTotalFund(computeNewDestinationFound(transferRequest.getAmount(), destinationAccount.getTotalFund()));

    // TODO: Set Conversion rate
    accountRepository.updateFundById(originAccount.getId(), originAccount.getTotalFund());
    accountRepository.updateFundById(destinationAccount.getId(), destinationAccount.getTotalFund());

    Transfer transfer = Transfer.builder()
        .destinationAccountNumber(transferRequest.getDestinationAccountNumber())
        .amount(transferRequest.getAmount())
        .currency(transferRequest.getCurrency())
        .taxRate(computeTaxRate(transferRequest.getAmount()))
        .conversionRate(CAD_RATE)
        .createdAt(LocalDateTime.now())
        .successful(Boolean.TRUE)
        .description(transferRequest.getDescription())
        .account(originAccount)
        .build();

    UUID uuid = transferRepository.create(transfer);

    return TransferResponse.builder()
        .id(uuid)
        .taxCollected(taxCollected)
        .cad(computeCadCoversion(transfer.getAmount(), CAD_RATE))
        .build();
  }

  private BigDecimal computeCadCoversion(BigDecimal transferAmount, BigDecimal cadRate) {
    return transferAmount.multiply(cadRate);
  }

  private BigDecimal computeTaxRate(BigDecimal transferAmount) {
    if (transferAmount.compareTo(LIMIT_NEW_RATE) > 0) {
      return TAX_RATE_FIVE;
    } else {
      return TAX_RATE_TWO;
    }
  }

  private BigDecimal computeTax(BigDecimal transferAmount) {
    BigDecimal taxRate = computeTaxRate(transferAmount);
    return transferAmount.multiply(taxRate);
  }

  private BigDecimal computeNewOriginFound(BigDecimal originFund, BigDecimal transferAmount, BigDecimal taxCollected) {
    return originFund.subtract(transferAmount).subtract(taxCollected);
  }

  private BigDecimal computeNewDestinationFound(BigDecimal transferAmount, BigDecimal destinationFund) {
    return destinationFund.add(transferAmount);
  }

}

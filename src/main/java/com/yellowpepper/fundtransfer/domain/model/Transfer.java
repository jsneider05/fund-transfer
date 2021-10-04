package com.yellowpepper.fundtransfer.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {

  private UUID id;

//  private Long originAccountNumber;

  private Long destinationAccountNumber;

  private BigDecimal amount;

  private CurrencyEnum currency;

  private BigDecimal taxRate;

  private BigDecimal conversionRate;

  private boolean successful;

  private LocalDateTime createdAt;

  private String description;

  private Account account;

}

package com.yellowpepper.fundtransfer.application.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yellowpepper.fundtransfer.domain.model.CurrencyEnum;
import java.math.BigDecimal;
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
public class TransferRequest {

  @JsonProperty(value = "origin_account")
  private Long originAccountNumber;

  @JsonProperty(value = "destination_account")
  private Long destinationAccountNumber;

  private BigDecimal amount;

  private CurrencyEnum currency;

  private String description;

}

package com.yellowpepper.fundtransfer.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
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
public class TransferResponse {

  @JsonProperty(value = "id")
  private UUID id;

  @JsonProperty(value = "tax_collected")
  private BigDecimal taxCollected;

  private BigDecimal cad;

}

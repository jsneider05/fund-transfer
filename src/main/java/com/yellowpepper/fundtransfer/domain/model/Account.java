package com.yellowpepper.fundtransfer.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

  private UUID id;

  private Long accountNumber;

  private BigDecimal totalFund;

  private CurrencyEnum currency;

  private boolean enabled;

  private LocalDateTime createdAt;

  private UUID userId;

  public void setTotalFund(BigDecimal totalFund) {
    this.totalFund = totalFund;
  }

  //  private List<Transfer> transfers = new ArrayList<>();

//  public void addTransfer(Transfer transfer) {
//    if (!this.transfers.contains(transfer)) {
//      this.transfers.add(transfer);
//      transfer.setAccount(this);
//    }
//  }
//
//  public void removeTransfer(Transfer transfer) {
//    if (this.transfers.contains(transfer)) {
//      this.transfers.remove(transfer);
//      transfer.setAccount(null);
//    }
//  }

}

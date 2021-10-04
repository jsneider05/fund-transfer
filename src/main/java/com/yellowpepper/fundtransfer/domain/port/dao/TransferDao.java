package com.yellowpepper.fundtransfer.domain.port.dao;

import com.yellowpepper.fundtransfer.domain.model.Transfer;
import java.time.LocalDateTime;

public interface TransferDao {

//  Transfer findByAccountNumber(Long accountNumber);

  Long countAllByOriginAccountNumberAndCreatedAt(Long originAccountNumber, LocalDateTime createdAt);

}

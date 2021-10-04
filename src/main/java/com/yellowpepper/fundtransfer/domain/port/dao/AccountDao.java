package com.yellowpepper.fundtransfer.domain.port.dao;

import com.yellowpepper.fundtransfer.domain.model.Account;

public interface AccountDao {

  Account findByAccountNumber(Long accountNumber);

}

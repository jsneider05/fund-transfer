package com.yellowpepper.fundtransfer.domain.port.repository;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountRepository {

  Integer updateFundById(UUID id, BigDecimal fund);

}

package com.yellowpepper.fundtransfer.domain.port.repository;

import com.yellowpepper.fundtransfer.domain.model.Transfer;
import java.util.UUID;

public interface TransferRepository {

  UUID create(Transfer transfer);

}

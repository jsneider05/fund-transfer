package com.yellowpepper.fundtransfer.application.handler;

import com.yellowpepper.fundtransfer.application.request.TransferRequest;
import com.yellowpepper.fundtransfer.application.response.TransferResponse;
import com.yellowpepper.fundtransfer.domain.service.CreateTransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateTransferHandler {

  private final CreateTransferService service;

  @Transactional
  public TransferResponse execute(TransferRequest request) {
    return service.execute(request);
  }

}

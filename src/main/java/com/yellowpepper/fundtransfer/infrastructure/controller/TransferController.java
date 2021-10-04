package com.yellowpepper.fundtransfer.infrastructure.controller;

import com.yellowpepper.fundtransfer.application.handler.CreateTransferHandler;
import com.yellowpepper.fundtransfer.application.request.TransferRequest;
import com.yellowpepper.fundtransfer.application.response.TransferResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
@RestController
@RequestMapping(path = "/v1/transfer")
@Tag(name = "FundTransfer", description = "Funds Transfers")
@Validated
public class TransferController {

  private final CreateTransferHandler createTransferHandler;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAuthority('fund:write')")
  @Operation(summary = "Create Transfer", description = "Transfer creation", tags = { "transfer" })
  public TransferResponse createTransfer(@Valid @RequestBody TransferRequest request) {
    return this.createTransferHandler.execute(request);
  }

}

package com.yellowpepper.fundtransfer.domain.exception;

public class TransferLimitException extends RuntimeException {

  public TransferLimitException(String message) {
    super(message);
  }
}

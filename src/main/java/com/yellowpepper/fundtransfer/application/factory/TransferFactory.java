package com.yellowpepper.fundtransfer.application.factory;

import com.yellowpepper.fundtransfer.application.request.TransferRequest;
import com.yellowpepper.fundtransfer.domain.model.Transfer;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class TransferFactory {

//  public final Function<TransferRequest, Transfer> toTransfer = request ->
//      Transfer.builder()
//          .originAccountNumber(request.getOriginAccountNumber())
//          .destinationAccountNumber(request.getDestinationAccountNumber())
//          .amount(request.getAmount())
//          .currency(request.getCurrency())
//          .description(request.getDescription())
//          .build();

}

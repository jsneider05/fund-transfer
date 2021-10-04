package com.yellowpepper.fundtransfer.domain.validator;

import com.yellowpepper.fundtransfer.domain.model.Account;
import java.math.BigDecimal;
import java.util.function.Predicate;

public interface AccountValidator extends Validation<Account> {

  static AccountValidator hasEnoughFund(BigDecimal amount) {
    return holds(account -> amount.compareTo(account.getTotalFund()) > 0, "Not enough founds");
  }

  static AccountValidator hasTransferPerDay(Boolean hasTransferPerDay) {
    return holds(account -> hasTransferPerDay, "Limit exceeded");
  }

  private static AccountValidator holds(Predicate<Account> p, String message) {
    return account -> p.negate().test(account) ? ValidationResult.ok() : ValidationResult.fail(message);
  }

}

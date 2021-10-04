package com.yellowpepper.fundtransfer.infrastructure.adapter;

import com.yellowpepper.fundtransfer.infrastructure.entity.AccountEntity;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {


  @Query("SELECT a FROM AccountEntity a" +
      " WHERE a.accountNumber = :account_number")
  Optional<AccountEntity> findByAccountNumber(@Param("account_number") Long accountNumber);

  @Modifying
  @Query("UPDATE AccountEntity a SET " +
      "   a.totalFund = :fund " +
      " WHERE a.id = :id")
  Integer updateFundById(@Param("id") UUID id, @Param("fund") BigDecimal fund);

}

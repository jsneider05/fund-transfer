package com.yellowpepper.fundtransfer.infrastructure.entity;

import com.yellowpepper.fundtransfer.domain.model.CurrencyEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TransferEntity")
@Table(name = "transfer")
public class TransferEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, updatable = false)
  @Type(type = "uuid-char")
  private UUID id;

//  @Column(name = "origin_account_number", nullable = false, updatable = false)
//  private Long originAccountNumber;

  @Column(name = "destination_account_number", nullable = false, updatable = false)
  private Long destinationAccountNumber;

  @Column(name = "amount", nullable = false, updatable = false, precision = 19, scale = 4)
  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  @Column(name = "currency", nullable = false, updatable = false)
  private CurrencyEnum currency;

  @Column(name = "tax_rate", nullable = false, updatable = false, precision = 19, scale = 18)
  private BigDecimal taxRate;

  @Column(name = "conversion_rate", nullable = false, updatable = false, precision = 19, scale = 18)
  private BigDecimal conversionRate;

  @Column(name = "successful", nullable = false, updatable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
  private boolean successful;

  @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
  private LocalDateTime createdAt;

  @Column(name = "description", nullable = false, updatable = false, length = 100)
  private String description;

  @ManyToOne
  @JoinColumn(
      name = "origin_account_number",
      nullable = false,
      updatable = false,
      referencedColumnName = "account_number",
      foreignKey = @ForeignKey(name = "transfer_account_account_number_fk")
  )
  private AccountEntity account;

}

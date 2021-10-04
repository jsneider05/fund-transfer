package com.yellowpepper.fundtransfer.infrastructure.entity;

import com.yellowpepper.fundtransfer.domain.model.CurrencyEnum;
import com.yellowpepper.security.auth.entity.UserEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Entity(name = "AccountEntity")
@Table(
    name = "account",
    uniqueConstraints = {
        @UniqueConstraint(name = "account_account_number_unique", columnNames = "account_number")
    }
)
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, updatable = false)
  @Type(type = "uuid-char")
  private UUID id;

  @Column(name = "account_number", nullable = false, updatable = false)
  private Long accountNumber;

  @Column(name = "total_fund", nullable = false, precision = 19, scale = 4)
  private BigDecimal totalFund;

  @Enumerated(EnumType.STRING)
  @Column(name = "currency", nullable = false)
  private CurrencyEnum currency;

  @Column(name = "enabled", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
  private boolean enabled;

  @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "user_id",
      nullable = false,
      referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "account_user_user_id_fk")
  )
  private UserEntity user;

}

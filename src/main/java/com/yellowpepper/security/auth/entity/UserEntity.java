package com.yellowpepper.security.auth.entity;

import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Entity(name = "UserEntity")
@Table(
    name = "user",
    uniqueConstraints = {
        @UniqueConstraint(name = "user_user_name_unique", columnNames = "user_name"),
        @UniqueConstraint(name = "user_email_unique", columnNames = "email")
    }
)
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, updatable = false)
  @Type(type = "uuid-char")
  private UUID id;

  @Column(name = "user_name", nullable = false)
  private String userName;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "enabled", nullable = false)
  private boolean enabled;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id",
          foreignKey = @ForeignKey(name = "user_role_user_id_fk")
      ),
      inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id",
          foreignKey = @ForeignKey(name = "user_role_role_id_fk")
      )
  )
  private Set<RoleEntity> roles;

}

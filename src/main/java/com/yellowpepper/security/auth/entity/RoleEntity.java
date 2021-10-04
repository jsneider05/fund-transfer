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
@Entity(name = "RoleEntity")
@Table(name = "role")
public class RoleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, updatable = false)
  @Type(type="uuid-char")
  private UUID id;

  @Column(name = "name", nullable = false, length = 255)
  private String name;

  @ManyToMany(mappedBy = "roles")
  private Set<UserEntity> users;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
      name = "role_privilege",
      joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id",
          foreignKey = @ForeignKey(name = "role_privilege_role_id_fk")
      ),
      inverseJoinColumns = @JoinColumn(
          name = "privilege_id", referencedColumnName = "id",
          foreignKey = @ForeignKey(name = "role_privilege_privilege_id_fk")
      )
  )
  private Set<PrivilegeEntity> privileges;

}

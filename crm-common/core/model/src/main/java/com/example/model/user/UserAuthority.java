package com.example.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authority")
public class UserAuthority {

  @Id
  @SequenceGenerator(
      name = "auth_seq",
      sequenceName = "auth_seq",
      allocationSize = 1,
      initialValue = 100000)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_seq")
  private Long id;

  @Column private String authority;

  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "role")
  private Set<User> users;

  public UserAuthority(Long id, String authority) {
    this.id = id;
    this.authority = authority;
  }
}

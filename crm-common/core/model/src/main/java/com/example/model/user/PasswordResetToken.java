package com.example.model.user;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "password_reset_token")
public class PasswordResetToken {

  @Id
  @SequenceGenerator(
      name = "pass_res_seq",
      sequenceName = "pass_res_seq",
      initialValue = 1,
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pass_res_seq")
  private Long id;

  @Column private String token;

  @Column private Instant expiryDate;

  @JoinColumn(name = "user_id")
  @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
  private User user;
}

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
@Table(name = "refresh_token")
public class Token {

    @Id
    @Column
    @SequenceGenerator(name = "token_seq",
                        sequenceName = "token_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_seq")
    private Long id;

    @Column
    private String subject;

    @Column
    private String token;

    @Column(name = "expiry_date")
    private Instant expiryDate;
}

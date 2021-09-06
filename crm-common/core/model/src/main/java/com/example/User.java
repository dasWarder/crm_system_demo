package com.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usr")
public class User {

    @Id
    @Column
    @SequenceGenerator(name = "usr_seq", sequenceName = "usr_seq",
                        initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_seq")
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private boolean enabled;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "user",
              cascade = CascadeType.ALL, orphanRemoval = true)
    private TodoList todoList;
}

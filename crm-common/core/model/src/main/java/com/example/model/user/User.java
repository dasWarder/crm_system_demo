package com.example.model.user;

import com.example.model.contactManager.Contact;
import com.example.model.report.Report;
import com.example.model.todoList.TodoList;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usr")
public class User {

  @Id
  @Column
  @SequenceGenerator(name = "usr_seq", sequenceName = "usr_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_seq")
  private Long id;

  @Column private String email;

  @Column private String password;

  @Column private boolean enabled;

  @Column(name = "registration_date")
  private LocalDate registrationDate;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "role_id")
  private UserAuthority role;

  @PrimaryKeyJoinColumn
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private TodoList todoList;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Contact contact;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Report> reports = new ArrayList<>();
}

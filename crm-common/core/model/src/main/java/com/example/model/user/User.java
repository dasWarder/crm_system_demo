package com.example.model.user;

import com.example.model.contactManager.Contact;
import com.example.model.report.Report;
import com.example.model.todoList.TodoList;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDate registrationDate;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "role_id")
  private UserAuthority role;

  @PrimaryKeyJoinColumn
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private TodoList todoList;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Contact contact;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Report> reports = new ArrayList<>();

}

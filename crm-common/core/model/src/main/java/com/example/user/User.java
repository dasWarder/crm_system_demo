package com.example.user;

import com.example.todoList.TodoList;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

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

    @ManyToOne(fetch =
            FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private UserAuthority role;

    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "user",
              cascade = CascadeType.ALL, orphanRemoval = true)
    private TodoList todoList = new TodoList(this.id, new ArrayList<>(), this);

    public User(Long id, String email, String password, boolean enabled, LocalDate registrationDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registrationDate = registrationDate;
    }
}

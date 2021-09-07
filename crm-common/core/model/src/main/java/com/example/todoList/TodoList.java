package com.example.todoList;

import com.example.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todo_list")
public class TodoList {

    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    @MapsId
    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}

package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @Column
    @SequenceGenerator(name = "task_seq", sequenceName = "task_seq",
                        initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column(name = "start_from")
    private LocalDateTime startFrom;

    @Column
    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "todo_list_id")
    private TodoList todoList;
}

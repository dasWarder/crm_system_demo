package com.example.service.todoList;

import com.example.model.todoList.TodoList;
import com.example.model.user.User;

import java.time.LocalDate;

public class TodoListTestData {

    public static final User TEST_USER_1 =
            User.builder()
                    .id(1L)
                    .email("test@gmail.com")
                    .password("12345")
                    .enabled(true)
                    .registrationDate(LocalDate.of(2020, 12, 12))
                    .build();

    public static final TodoList TEST_TODO_1 = TodoList.builder().id(1L).build();
}

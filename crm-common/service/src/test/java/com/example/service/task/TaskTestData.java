package com.example.service.task;

import com.example.model.todoList.Task;

import java.time.LocalDateTime;


public class TaskTestData {

    public static final Long WRONG_ID = 123456789L;

    public static final Task TEST_TASK_1 = Task.builder()
                                                    .id(1L)
                                                    .title("Create migration")
                                                    .description("It is necessary to create a brand new migration file")
                                                    .startFrom(LocalDateTime.now().plusDays(1))
                                                    .deadline(LocalDateTime.now().plusDays(2))
                                                    .build();

    public static final Task TEST_TASK_2 = Task.builder()
                                                    .id(2L)
                                                    .title("Create repository")
                                                    .description("Create a new repository and a service layer for Person entity")
                                                    .startFrom(LocalDateTime.now().plusDays(2))
                                                    .deadline(LocalDateTime.now().plusDays(3))
                                                    .build();

    public static final Task TEST_TASK_3 = Task.builder()
                                                    .id(3L)
                                                    .title("Update details information")
                                                    .description("Update a model and DTO classes for DetailsInformation")
                                                    .startFrom(LocalDateTime.now().plusDays(3))
                                                    .deadline(LocalDateTime.now().plusDays(4))
                                                    .build();

    public static final Task TEST_SAVE_TASK = Task.builder()
                                                    .id(4L)
                                                    .title("Add todo list")
                                                    .description("Create todo list")
                                                    .startFrom(LocalDateTime.of(2021, 9, 12, 12, 00, 00))
                                                    .deadline(LocalDateTime.of(2021, 9, 13, 12, 00, 00))
                                                    .build();

    public static final Task TEST_UPDATE_TASK = Task.builder()
                                                    .id(TEST_TASK_2.getId())
                                                    .title("Update title")
                                                    .description("Updated description")
                                                    .startFrom(LocalDateTime.of(2021, 9, 12, 12, 00, 00))
                                                    .deadline(LocalDateTime.of(2021, 9, 15, 23, 59, 59))
                                                    .build();
}

package com.example.contactService.data;

import com.example.model.todoList.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TestTaskData {

    public static final Task TEST_TASK_1 = Task.builder()
                                                        .id(1L)
                                                        .title("Create migration")
                                                        .description("It is necessary to create a brand new migration file")
                                                        .startFrom(LocalDateTime.of(2021, 9, 2, 10, 13, 00))
                                                        .deadline(LocalDateTime.of(2021, 9, 3, 23, 59, 59))
                                                        .build();

    public static final Task TEST_TASK_2 = Task.builder()
                                                        .id(2L)
                                                        .title("Create repository")
                                                        .description("Create a new repository and a service layer for Person entity")
                                                        .startFrom(LocalDateTime.of(2021,9, 3, 11, 00 ,00))
                                                        .deadline(LocalDateTime.of(2021, 9, 4, 17, 00, 00))
                                                        .build();

    public static final Task TEST_TASK_3 = Task.builder()
                                                        .id(3L)
                                                        .title("Update details information")
                                                        .description("Update a model and DTO classes for DetailsInformation")
                                                        .startFrom(LocalDateTime.of(2021, 9, 5, 9, 00, 00))
                                                        .deadline(LocalDateTime.of(2021, 9, 6, 13, 00 ,00))
                                                        .build();

    public static final Task TEST_TASK_4 = Task.builder()
                                                        .id(4L)
                                                        .title("Create something")
                                                        .description("Create some stuff")
                                                        .startFrom(LocalDateTime.of(2200, 9, 11, 12, 00, 00))
                                                        .deadline(LocalDateTime.of(2200, 9, 12, 12, 00, 00))
                                                        .build();

    public static final Task TEST_SAVE_TASK = Task.builder()
                                                        .id(5L)
                                                        .title("Add todo list")
                                                        .description("Create todo list")
                                                        .startFrom(LocalDateTime.of(2021, 9, 3, 12, 00, 00))
                                                        .deadline(LocalDateTime.of(2021, 9, 4, 12, 00, 00))
                                                        .build();

    public static final Task TEST_UPDATE_TASK = Task.builder()
                                                        .id(TEST_TASK_2.getId())
                                                        .title("Update title")
                                                        .description("Updated description")
                                                        .startFrom(LocalDateTime.of(2021, 9, 1, 12, 00, 00))
                                                        .deadline(LocalDateTime.of(2021, 9, 4, 23, 59, 59))
                                                        .build();
}

package com.example.contactService.integration;

import com.example.AbstractTest;
import com.example.exception.TaskNotFoundException;
import com.example.exception.UnsupportedParameterException;
import com.example.service.task.TaskService;
import com.example.todoList.Task;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.contactService.data.TestData.TEST_PAGEABLE;
import static com.example.contactService.data.TestTaskData.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Sql(scripts = { "classpath:/db/task/populate_todo_related_tables.sql" })
public class TaskServiceImplTest extends AbstractTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void shouldSaveTaskProperly() {

        log.info("Test saveTask() method");
        Task storedTask = taskService.saveTask(TEST_SAVE_TASK);

        Assertions.assertNotNull(storedTask);
        assertThat(storedTask).usingRecursiveComparison()
                                .ignoringFields("todoList")
                                .isEqualTo(TEST_SAVE_TASK);
    }

    @Test
    public void shouldUpdateTaskByIdProperly() throws TaskNotFoundException {

        log.info("Test updateTaskById() method");
        Task updatedTask = taskService.updateTaskById(TEST_TASK_2.getId(), TEST_UPDATE_TASK);

        Assertions.assertNotNull(updatedTask);
        assertThat(updatedTask).usingRecursiveComparison()
                                                .ignoringFields("todoList")
                                                .isEqualTo(TEST_UPDATE_TASK);
    }

    @Test
    public void shouldGetTaskByIdProperly() throws TaskNotFoundException {

        log.info("Test  getTaskById() method");
        Task taskById = taskService.getTaskById(TEST_TASK_1.getId());

        Assertions.assertNotNull(taskById);
        assertThat(taskById).usingRecursiveComparison()
                                                .ignoringFields("todoList")
                                                .isEqualTo(TEST_TASK_1);
    }

    @Test
    public void shouldDeleteTaskByIdProperly() {

        log.info("Test deleteTaskById() method");
        taskService.deleteTaskById(TEST_TASK_3.getId());

        Assertions.assertThrows(TaskNotFoundException.class,
                            () -> taskService.getTaskById(TEST_TASK_3.getId()));
    }
    
    @Test
    public void shouldGetTasksProperly() {

        log.info("Test getTasks() method");
        List<Task> allTasksList = Stream.of(TEST_TASK_1, TEST_TASK_2,
                                            TEST_TASK_3, TEST_TASK_4)
                                            .collect(Collectors.toList());
        Page<Task> tasks = taskService.getTasks(TEST_PAGEABLE);
        List<Task> actualContent = tasks.getContent();

        Assertions.assertNotNull(tasks);
        assertThat(actualContent).usingRecursiveComparison()
                                                    .ignoringFields("todoList")
                                                    .isEqualTo(allTasksList);
    }

    @Test
    public void shouldGetActualTasksProperly() {

        log.info("Test getActualTasks() method");
        List<Task> expectedTasks = Stream.of(TEST_TASK_4).collect(Collectors.toList());

        Page<Task> activeTasks = taskService.getActiveTasks(TEST_PAGEABLE);
        List<Task> actualTasks = activeTasks.getContent();

        Assertions.assertNotNull(actualTasks);
        assertThat(actualTasks).usingRecursiveComparison()
                                                        .ignoringFields("todoList")
                                                        .isEqualTo(expectedTasks);
    }

    @Test
    public void shouldGetTasksFroDateProperly() {

        log.info("Test getTasksFromDate() method");
        List<Task> expectedTasks = Stream.of(TEST_TASK_3, TEST_TASK_4)
                                            .collect(Collectors.toList());
        LocalDateTime findDate = LocalDateTime.of(2021, 9, 4, 00, 00, 00);
        Page<Task> tasksFromDate = taskService.getTasksFromDate(findDate, TEST_PAGEABLE);
        List<Task> actualTasks = tasksFromDate.getContent();

        Assertions.assertNotNull(tasksFromDate);
        assertThat(actualTasks).usingRecursiveComparison()
                                                            .ignoringFields("todoList")
                                                            .isEqualTo(expectedTasks);
    }

    @Test
    public void shouldGetMissedTasksProperly() {

        log.info("Test getMissedTasks() method");
        List<Task> expectedTasks = Stream.of(TEST_TASK_1, TEST_TASK_2, TEST_TASK_3)
                                                                    .collect(Collectors.toList());
        List<Task> actualTasks = taskService.getMissedDeadlineTasks(TEST_PAGEABLE)
                                                                        .getContent();

        Assertions.assertNotNull(actualTasks);
        assertThat(actualTasks).usingRecursiveComparison()
                                                    .ignoringFields("todoList")
                                                    .isEqualTo(expectedTasks);
    }

    @Test
    public void shouldGetActualTasksBetweenProperly() {

        log.info("Test getActualTasksBetween() method");
        LocalDateTime start = LocalDateTime.of(2021, 9, 1, 00, 00, 00);
        LocalDateTime end = LocalDateTime.of(2021, 10, 1, 00, 00, 00);
        List<Task> expectedTasks = Stream.of(TEST_TASK_1, TEST_TASK_2, TEST_TASK_3)
                                                                    .collect(Collectors.toList());
        List<Task> actualTasks = taskService.getActiveTasksBetween(start, end, TEST_PAGEABLE)
                                                                                    .getContent();
        Assertions.assertNotNull(actualTasks);
        assertThat(actualTasks).usingRecursiveComparison()
                                                        .ignoringFields("todoList")
                                                        .isEqualTo(expectedTasks);
    }

    @Test
    public void shouldGetTasksByParamProperly() throws UnsupportedParameterException {

        log.info("Test getTasksByParam() method");
        String[] filters = new String[]{ "title" };
        String query = "Create";
        List<Task> expectedTasks = Stream.of(TEST_TASK_1, TEST_TASK_2, TEST_TASK_4)
                                                                                .collect(Collectors.toList());
        List<Task> actualTasks = taskService.getTasksByParams(filters, query, TEST_PAGEABLE)
                                                                                    .getContent();
        Assertions.assertNotNull(actualTasks);
        assertThat(actualTasks).usingRecursiveComparison()
                                                            .ignoringFields("todoList")
                                                            .isEqualTo(expectedTasks);
    }

}

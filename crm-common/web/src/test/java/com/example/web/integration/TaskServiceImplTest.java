//package com.example.web.integration;
//
//import com.example.web.AbstractTest;
//import com.example.exception.TaskNotFoundException;
//import com.example.exception.TodoListNotFoundException;
//import com.example.exception.UnsupportedParameterException;
//import com.example.exception.UserNotFoundException;
//import com.example.service.task.TaskService;
//import com.example.model.todoList.Task;
//import com.example.web.data.TestTaskData;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.jdbc.Sql;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static com.example.web.data.TestData.TEST_PAGEABLE;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Slf4j
//@WithMockUser(username = "test@gmail.com", authorities = "USER")
//@Sql(scripts = {"classpath:/db/todoList/populate_todo_related_tables.sql"})
//public class TaskServiceImplTest extends AbstractTest {
//
//    @Autowired
//    private TaskService taskService;
//
//    @Test
//    public void shouldSaveTaskProperly() throws UserNotFoundException, TodoListNotFoundException {
//
//        log.info("Test saveTask() method");
//        Task storedTask = taskService.saveTask(TestTaskData.TEST_SAVE_TASK);
//
//        Assertions.assertNotNull(storedTask);
//        assertThat(storedTask).usingRecursiveComparison()
//                                .ignoringFields("todoList")
//                                .isEqualTo(TestTaskData.TEST_SAVE_TASK);
//    }
//
//    @Test
//    public void shouldUpdateTaskByIdProperly() throws TaskNotFoundException {
//
//        log.info("Test updateTaskById() method");
//        Task updatedTask = taskService.updateTaskById(TestTaskData.TEST_TASK_2.getId(), TestTaskData.TEST_UPDATE_TASK);
//
//        Assertions.assertNotNull(updatedTask);
//        assertThat(updatedTask).usingRecursiveComparison()
//                                                .ignoringFields("todoList")
//                                                .isEqualTo(TestTaskData.TEST_UPDATE_TASK);
//    }
//
//    @Test
//    public void shouldGetTaskByIdProperly() throws TaskNotFoundException {
//
//        log.info("Test  getTaskById() method");
//        Task taskById = taskService.getTaskById(TestTaskData.TEST_TASK_1.getId());
//
//        Assertions.assertNotNull(taskById);
//        assertThat(taskById).usingRecursiveComparison()
//                                                .ignoringFields("todoList")
//                                                .isEqualTo(TestTaskData.TEST_TASK_1);
//    }
//
//    @Test
//    public void shouldDeleteTaskByIdProperly() {
//
//        log.info("Test deleteTaskById() method");
//        taskService.deleteTaskById(TestTaskData.TEST_TASK_3.getId());
//
//        Assertions.assertThrows(TaskNotFoundException.class,
//                            () -> taskService.getTaskById(TestTaskData.TEST_TASK_3.getId()));
//    }
//
//    @Test
//    public void shouldGetTasksProperly() {
//
//        log.info("Test getTasks() method");
//        List<Task> allTasksList = Stream.of(TestTaskData.TEST_TASK_1, TestTaskData.TEST_TASK_2,
//                                            TestTaskData.TEST_TASK_3, TestTaskData.TEST_TASK_4)
//                                            .collect(Collectors.toList());
//        Page<Task> tasks = taskService.getTasks(TEST_PAGEABLE);
//        List<Task> actualContent = tasks.getContent();
//
//        Assertions.assertNotNull(tasks);
//        assertThat(actualContent).usingRecursiveComparison()
//                                                    .ignoringFields("todoList")
//                                                    .isEqualTo(allTasksList);
//    }
//
//    @Test
//    public void shouldGetActualTasksProperly() {
//
//        log.info("Test getActualTasks() method");
//        List<Task> expectedTasks = Stream.of(TestTaskData.TEST_TASK_4).collect(Collectors.toList());
//
//        Page<Task> activeTasks = taskService.getActiveTasks(TEST_PAGEABLE);
//        List<Task> actualTasks = activeTasks.getContent();
//
//        Assertions.assertNotNull(actualTasks);
//        assertThat(actualTasks).usingRecursiveComparison()
//                                                        .ignoringFields("todoList")
//                                                        .isEqualTo(expectedTasks);
//    }
//
//    @Test
//    public void shouldGetTasksFroDateProperly() {
//
//        log.info("Test getTasksFromDate() method");
//        List<Task> expectedTasks = Stream.of(TestTaskData.TEST_TASK_3, TestTaskData.TEST_TASK_4)
//                                            .collect(Collectors.toList());
//        LocalDateTime findDate = LocalDateTime.of(2021, 9, 4, 00, 00, 00);
//        Page<Task> tasksFromDate = taskService.getTasksFromDate(findDate, TEST_PAGEABLE);
//        List<Task> actualTasks = tasksFromDate.getContent();
//
//        Assertions.assertNotNull(tasksFromDate);
//        assertThat(actualTasks).usingRecursiveComparison()
//                                                            .ignoringFields("todoList")
//                                                            .isEqualTo(expectedTasks);
//    }
//
//    @Test
//    public void shouldGetMissedTasksProperly() {
//
//        log.info("Test getMissedTasks() method");
//        List<Task> expectedTasks = Stream.of(TestTaskData.TEST_TASK_1, TestTaskData.TEST_TASK_2, TestTaskData.TEST_TASK_3)
//                                                                    .collect(Collectors.toList());
//        List<Task> actualTasks = taskService.getMissedDeadlineTasks(TEST_PAGEABLE)
//                                                                        .getContent();
//
//        Assertions.assertNotNull(actualTasks);
//        assertThat(actualTasks).usingRecursiveComparison()
//                                                    .ignoringFields("todoList")
//                                                    .isEqualTo(expectedTasks);
//    }
//
//    @Test
//    public void shouldGetActualTasksBetweenProperly() {
//
//        log.info("Test getActualTasksBetween() method");
//        LocalDateTime start = LocalDateTime.of(2021, 9, 1, 00, 00, 00);
//        LocalDateTime end = LocalDateTime.of(2021, 10, 1, 00, 00, 00);
//        List<Task> expectedTasks = Stream.of(TestTaskData.TEST_TASK_1, TestTaskData.TEST_TASK_2, TestTaskData.TEST_TASK_3)
//                                                                    .collect(Collectors.toList());
//        List<Task> actualTasks = taskService.getActiveTasksBetween(start, end, TEST_PAGEABLE)
//                                                                                    .getContent();
//        Assertions.assertNotNull(actualTasks);
//        assertThat(actualTasks).usingRecursiveComparison()
//                                                        .ignoringFields("todoList")
//                                                        .isEqualTo(expectedTasks);
//    }
//
//    @Test
//    public void shouldGetTasksByParamProperly() throws UnsupportedParameterException {
//
//        log.info("Test getTasksByParam() method");
//        String[] filters = new String[]{ "title" };
//        String query = "Create";
//        List<Task> expectedTasks = Stream.of(TestTaskData.TEST_TASK_1, TestTaskData.TEST_TASK_2, TestTaskData.TEST_TASK_4)
//                                                                                .collect(Collectors.toList());
//        List<Task> actualTasks = taskService.getTasksByParams(filters, query, TEST_PAGEABLE)
//                                                                                    .getContent();
//        Assertions.assertNotNull(actualTasks);
//        assertThat(actualTasks).usingRecursiveComparison()
//                                                            .ignoringFields("todoList")
//                                                            .isEqualTo(expectedTasks);
//    }
//
//}

package com.example.service.task;

import com.example.exception.TaskNotFoundException;
import com.example.repository.TaskRepository;
import com.example.service.specification.TaskSpecification;
import com.example.todoList.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.service.contact.ContactTestData.TEST_PAGEABLE;
import static com.example.service.task.TaskTestData.*;

@Slf4j
@RequiredArgsConstructor
class TaskServiceImplTest {

    private final TaskRepository taskRepository = Mockito.mock(TaskRepository.class);

    private final TaskSpecification taskSpecification = Mockito.mock(TaskSpecification.class);

    private final TaskService taskService = new TaskServiceImpl(taskRepository, taskSpecification);

    @Test
    public void shouldSaveTaskProperly() {

        log.info("Test saveTask() method of the TaskService class");
        Mockito.when(taskRepository.save(TEST_SAVE_TASK)).thenReturn(TEST_SAVE_TASK);

        Task actualTask = taskService.saveTask(TEST_SAVE_TASK);

        Assertions.assertNotNull(actualTask);
        Assertions.assertEquals(TEST_SAVE_TASK, actualTask);
    }

    @Test
    public void shouldUpdateTaskProperly() throws TaskNotFoundException {

        log.info("Test updateTask() method of the TaskService class");
        Mockito.when(taskRepository.findById(TEST_TASK_2.getId())).thenReturn(Optional.of(TEST_TASK_2));
        Mockito.when(taskRepository.save(TEST_UPDATE_TASK)).thenReturn(TEST_UPDATE_TASK);

        Task actualTask = taskService.updateTaskById(TEST_TASK_2.getId(), TEST_UPDATE_TASK);

        Assertions.assertNotNull(actualTask);
        Assertions.assertEquals(TEST_UPDATE_TASK, actualTask);
    }

    @Test
    public void shouldThrowExceptionWhenIdIsNull() {

        log.info("Test updateTask() method throw an exception when id is null");
        Assertions.assertThrows(TaskNotFoundException.class,
                () -> taskService.updateTaskById(WRONG_ID, TEST_UPDATE_TASK));
    }

    @Test
    public void shouldGetTaskByIdProperly() throws TaskNotFoundException {

        log.info("Test getTaskById() method of the TskService class");
        Mockito.when(taskRepository.findById(TEST_TASK_1.getId())).thenReturn(Optional.of(TEST_TASK_1));

        Task taskById = taskService.getTaskById(TEST_TASK_1.getId());

        Assertions.assertNotNull(taskById);
        Assertions.assertEquals(TEST_TASK_1, taskById);
    }

    @Test
    public void shouldThrowExceptionWhenGetByIdWithNullId() {

        log.info("Test getTaskById() method throws an exception when id is null");

        Assertions.assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(WRONG_ID));
    }

    @Test
    public void shouldGetDeleteTaskByIdProperly() {

        log.info("Test deleteTaskById() method of the TskService class");

        taskService.deleteTaskById(TEST_TASK_1.getId());
    }

    @Test
    public void shouldGetTasksProperly() {

        log.info("Test getTasks() method of the TaskService class");
        List<Task> allTasks = Stream.of(TEST_TASK_1, TEST_TASK_2, TEST_TASK_3)
                                                            .collect(Collectors.toList());
        PageImpl<Task> expectedTaskPage = new PageImpl<>(allTasks);

        Mockito.when(taskRepository.findAll(TEST_PAGEABLE)).thenReturn(expectedTaskPage);

        Page<Task> tasks = taskService.getTasks(TEST_PAGEABLE);

        Assertions.assertNotNull(tasks);
        Assertions.assertEquals(expectedTaskPage.getContent(), tasks.getContent());
    }

    @Test
    public void shouldGetTasksFromDateProperly() {

        log.info("Test getTasksFromDate() method of the TaskService class");
        LocalDateTime mockTime = LocalDateTime.now();
        List<Task> allTasks = Stream.of(TEST_TASK_1, TEST_TASK_2, TEST_TASK_3)
                                                                         .collect(Collectors.toList());
        PageImpl<Task> expectedTaskPage = new PageImpl<>(allTasks);
        Mockito.when(taskRepository.getTasksByStartFromAfter(mockTime, TEST_PAGEABLE)).thenReturn(expectedTaskPage);

        Page<Task> actualTasks = taskService.getTasksFromDate(mockTime, TEST_PAGEABLE);

        Assertions.assertNotNull(actualTasks);
        Assertions.assertEquals(expectedTaskPage.getContent(), actualTasks.getContent());
    }

    @Test
    public void shouldGetActiveTasksBetweenProperly() {

        log.info("Test getActiveTasksBetween() method of the TaskService class");
        LocalDateTime start = LocalDateTime.now().plusDays(2);
        LocalDateTime end = LocalDateTime.now().plusDays(5);
        List<Task> expectedTasksList = Stream.of(TEST_TASK_2, TEST_TASK_3)
                                                        .collect(Collectors.toList());
        PageImpl<Task> expectedTasks = new PageImpl<>(expectedTasksList);

        Mockito.when(taskRepository.getTasksByStartFromAfterAndDeadlineBefore(start, end, TEST_PAGEABLE)).thenReturn(expectedTasks);

        Page<Task> actualTasksBetween = taskService.getActiveTasksBetween(start, end, TEST_PAGEABLE);

        Assertions.assertNotNull(actualTasksBetween);
        Assertions.assertEquals(expectedTasks.getContent(), actualTasksBetween.getContent());
    }

}
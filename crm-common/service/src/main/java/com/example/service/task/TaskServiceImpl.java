package com.example.service.task;

import com.example.exception.TodoListNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.exception.TaskNotFoundException;
import com.example.exception.UnsupportedParameterException;
import com.example.model.todoList.Task;
import com.example.model.todoList.TodoList;
import com.example.model.user.User;
import com.example.repository.TaskRepository;
import com.example.service.specification.TaskSpecification;
import com.example.service.todoList.TodoListService;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final UserService userService;

  private final TaskRepository taskRepository;

  private final TodoListService todoListService;

  private final TaskSpecification taskSpecification;

  @Override
  @Transactional
  public Task saveTask(final Task task) throws UserNotFoundException, TodoListNotFoundException {

    log.info("Store a new task");

    User loggedUser = userService.getCurrentUser();
    TodoList todoListById = todoListService.getTodoListById(loggedUser.getId());
    task.setTodoList(todoListById);

    Task storedTask = taskRepository.save(task);

    return storedTask;
  }

  @Override
  @Transactional
  public Task updateTaskById(final Long id, Task updateTask) throws TaskNotFoundException {

    log.info("Update a task with id = {}", id);
    Task dbTask =
        taskRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new TaskNotFoundException(
                        String.format("The task with id = %d not found", id)));
    updateTask.setId(dbTask.getId());
    updateTask.setTodoList(dbTask.getTodoList());
    Task updatedTask = taskRepository.save(updateTask);

    return updatedTask;
  }

  @Override
  @Transactional(readOnly = true)
  public Task getTaskById(final Long id) throws TaskNotFoundException {

    log.info("Get a task by its id = {}", id);
    Task validTaskById =
        taskRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new TaskNotFoundException(
                        String.format("The task with id = %d not found", id)));
    return validTaskById;
  }

  @Override
  @Transactional
  public void deleteTaskById(final Long id) {

    log.info("Delete a task by its id = {}", id);
    taskRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Task> getTasks(final Pageable pageable) {

    log.info("Get all tasks");
    Page<Task> allTasks = taskRepository.findAll(pageable);

    return allTasks;
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Task> getTasksByParams(
      final String[] filters, final String query, final Pageable pageable)
      throws UnsupportedParameterException {

    log.info("Get all tasks filtered by params");
    Specification<Task> tasksByParams = taskSpecification.findTasksByParams(filters, query);
    Page<Task> filteredTasks = taskRepository.findAll(tasksByParams, pageable);

    return filteredTasks;
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Task> getActiveTasks(final Pageable pageable) {

    log.info("Get all active tasks");
    LocalDateTime currentDateTime = LocalDateTime.now();
    Page<Task> activeTasks = taskRepository.getTasksByDeadlineAfter(currentDateTime, pageable);

    return activeTasks;
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Task> getTasksFromDate(final LocalDateTime startFrom, Pageable pageable) {

    log.info("Get all tasks started from date = {}", startFrom.toString());
    Page<Task> tasksFromDate = taskRepository.getTasksByStartFromAfter(startFrom, pageable);

    return tasksFromDate;
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Task> getMissedDeadlineTasks(final Pageable pageable) {

    log.info("Get all tasks with missed deadlines");
    LocalDateTime currentDateTime = LocalDateTime.now();
    Page<Task> missedTasks = taskRepository.getTasksByDeadlineBefore(currentDateTime, pageable);

    return missedTasks;
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Task> getActiveTasksBetween(
      final LocalDateTime startFrom, final LocalDateTime deadline, final Pageable pageable) {

    log.info("Get all active tasks between start = {} and end = {}", startFrom, deadline);
    Page<Task> tasksBetween =
        taskRepository.getTasksByStartFromAfterAndDeadlineBefore(startFrom, deadline, pageable);

    return tasksBetween;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Task> getLastActiveTasks(final Pageable pageable) {

    log.info("Get last active tasks");
    Page<Task> activeTasks = this.getActiveTasks(pageable);
    List<Task> lastActiveTasks =
        activeTasks.getContent().stream().limit(5).collect(Collectors.toList());

    return lastActiveTasks;
  }
}

package com.example.service.task;

import com.example.exception.TaskNotFoundException;
import com.example.exception.TodoListNotFoundException;
import com.example.exception.UnsupportedParameterException;
import com.example.exception.UserNotFoundException;
import com.example.model.todoList.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;


public interface TaskService {

  Task saveTask(final Task task) throws UserNotFoundException, TodoListNotFoundException;

  Task updateTaskById(final Long id, Task updateTask) throws TaskNotFoundException;

  Task getTaskById(final Long id) throws TaskNotFoundException;

  void deleteTaskById(final Long id);

  Page<Task> getTasks(final Pageable pageable);

  Page<Task> getTasksByParams(final String[] filters, final String query, final Pageable pageable)
      throws UnsupportedParameterException;

  Page<Task> getActiveTasks(final Pageable pageable);

  Page<Task> getTasksFromDate(final LocalDateTime startFrom, final Pageable pageable);

  Page<Task> getMissedDeadlineTasks(final Pageable pageable);

  Page<Task> getActiveTasksBetween(
      final LocalDateTime startFrom, final LocalDateTime deadline, final Pageable pageable);

  List<Task> getLastActiveTasks(final Pageable pageable);
}

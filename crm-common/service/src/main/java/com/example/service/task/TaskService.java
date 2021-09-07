package com.example.service.task;


import com.example.Task;
import com.example.exception.TaskNotFoundException;
import com.example.exception.UnsupportedParameterException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

/*
    Page<Task> getTasksByStartFromAfter(LocalDateTime startFrom, Pageable pageable);

    Page<Task> getTasksByDeadlineBefore(LocalDateTime deadline, Pageable pageable);

    Page<Task> getTasks(Pageable pageable);

    Page<Task> getTasksByDeadlineAfter(LocalDateTime date, Pageable pageable);

    Page<Task> getTasksByStartFromBeforeAndDeadlineAfter(LocalDateTime currentTime, Pageable pageable);

    Page<Task> getTasksByStartFromAfterAndDeadlineBefore(LocalDateTime startTime, LocalDateTime deadline, Pageable pageable);
 */
public interface TaskService {

    Task saveTask(final Task task);

    Task updateTaskById(final Long id, Task updateTask) throws TaskNotFoundException;

    Task getTaskById(final Long id) throws TaskNotFoundException;

    void deleteTaskById(final Long id);

    Page<Task> getTasks(final Pageable pageable);

    Page<Task> getTasksByParams(final String[] filters, final String query, final Pageable pageable) throws UnsupportedParameterException;

    Page<Task> getActiveTasks(final Pageable pageable);

    Page<Task> getTasksFromDate(final LocalDateTime startFrom, final Pageable pageable);

    Page<Task> getMissedDeadlineTasks(final Pageable pageable);

    Page<Task> getActiveTasksBetween(final LocalDateTime startFrom, final LocalDateTime deadline, final Pageable pageable);

}

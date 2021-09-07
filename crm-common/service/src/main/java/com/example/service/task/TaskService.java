package com.example.service.task;


import com.example.Task;
import com.example.exception.TaskNotFoundException;
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

    Task saveTask(Task task);

    Task updateTaskById(Long id, Task updateTask) throws TaskNotFoundException;

    Task getTaskById(Long id) throws TaskNotFoundException;

    void deleteTaskById(Long id);

    Page<Task> getTasks(Pageable pageable);

    Page<Task> getActiveTasks(Pageable pageable);

    Page<Task> getTasksFromDate(LocalDateTime startFrom, Pageable pageable);

    Page<Task> getMissedDeadlineTasks(Pageable pageable);

    Page<Task> getActiveTasksBetween(LocalDateTime startFrom, LocalDateTime deadline, Pageable pageable);

}

package com.example.service.task;

import com.example.Task;
import com.example.exception.TaskNotFoundException;
import com.example.exception.UnsupportedParameterException;
import com.example.repository.TaskRepository;
import com.example.service.specification.TaskSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskSpecification taskSpecification;

    @Override
    @Transactional
    public Task saveTask(final Task task) {

        log.info("Store a new task");
        Task storedTask = taskRepository.save(task);

        return storedTask;
    }

    @Override
    @Transactional
    public Task updateTaskById(final Long id, Task updateTask) throws TaskNotFoundException {

        log.info("Update a task with id = {}", id);
        Task dbTask = taskRepository.findById(id)
                                    .orElseThrow(TaskNotFoundException::new);
        updateTask.setId(dbTask.getId());
        Task updatedTask = taskRepository.save(updateTask);

        return updatedTask;
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskById(final Long id) throws TaskNotFoundException {

        log.info("Get a task by its id = {}", id);
        Task validTaskById = taskRepository.findById(id)
                                        .orElseThrow(TaskNotFoundException::new);
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
    public Page<Task> getTasksByParams(final String[] filters, final String query, final Pageable pageable) throws UnsupportedParameterException {

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
    public Page<Task> getActiveTasksBetween(final LocalDateTime startFrom, final LocalDateTime deadline, final Pageable pageable) {

        log.info("Get all active tasks between start = {} and end = {}", startFrom, deadline);
        Page<Task> tasksBetween = taskRepository.getTasksByStartFromAfterAndDeadlineBefore(startFrom, deadline, pageable);

        return tasksBetween;
    }
}

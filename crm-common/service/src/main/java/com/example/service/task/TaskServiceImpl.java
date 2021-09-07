package com.example.service.task;

import com.example.Task;
import com.example.exception.TaskNotFoundException;
import com.example.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task saveTask(Task task) {

        log.info("Store a new task");
        Task storedTask = taskRepository.save(task);

        return storedTask;
    }

    @Override
    public Task updateTaskById(Long id, Task updateTask) throws TaskNotFoundException {

        log.info("Update a task with id = {}", id);
        Task dbTask = taskRepository.findById(id)
                                    .orElseThrow(TaskNotFoundException::new);
        updateTask.setId(dbTask.getId());
        Task updatedTask = taskRepository.save(updateTask);

        return updatedTask;
    }

    @Override
    public Task getTaskById(Long id) throws TaskNotFoundException {

        log.info("Get a task by its id = {}", id);
        Task validTaskById = taskRepository.findById(id)
                                        .orElseThrow(TaskNotFoundException::new);
        return validTaskById;
    }

    @Override
    public void deleteTaskById(Long id) {

        log.info("Delete a task by its id = {}", id);
        taskRepository.deleteById(id);
    }

    @Override
    public Page<Task> getTasks(Pageable pageable) {

        log.info("Get all tasks");
        Page<Task> allTasks = taskRepository.findAll(pageable);

        return allTasks;
    }

    @Override
    public Page<Task> getActiveTasks(Pageable pageable) {

        log.info("Get all active tasks");
        LocalDateTime currentDateTime = LocalDateTime.now();
        Page<Task> activeTasks = taskRepository.getTasksByDeadlineAfter(currentDateTime, pageable);

        return activeTasks;
    }

    @Override
    public Page<Task> getTasksFromDate(LocalDateTime startFrom, Pageable pageable) {

        log.info("Get all tasks started from date = {}", startFrom.toString());
        Page<Task> tasksFromDate = taskRepository.getTasksByStartFromAfter(startFrom, pageable);

        return tasksFromDate;
    }

    @Override
    public Page<Task> getMissedDeadlineTasks(Pageable pageable) {

        log.info("Get all tasks with missed deadlines");
        LocalDateTime currentDateTime = LocalDateTime.now();
        Page<Task> missedTasks = taskRepository.getTasksByDeadlineBefore(currentDateTime, pageable);

        return missedTasks;
    }

    @Override
    public Page<Task> getActiveTasksBetween(LocalDateTime startFrom, LocalDateTime deadline, Pageable pageable) {

        log.info("Get all active tasks between start = {} and end = {}", startFrom, deadline);
        Page<Task> tasksBetween = taskRepository.getTasksByStartFromAfterAndDeadlineBefore(startFrom, deadline, pageable);

        return tasksBetween;
    }
}

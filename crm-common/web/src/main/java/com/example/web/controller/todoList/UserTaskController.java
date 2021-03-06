package com.example.web.controller.todoList;

import com.example.exception.TaskNotFoundException;
import com.example.exception.TodoListNotFoundException;
import com.example.exception.UnsupportedParameterException;
import com.example.exception.UserNotFoundException;
import com.example.mapper.task.TaskMapper;
import com.example.mapper.dto.task.TaskDto;
import com.example.model.todoList.Task;
import com.example.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/manage/todo/tasks")
public class UserTaskController {

  private final TaskMapper taskMapper;

  private final TaskService taskService;

  private static final String BASE_URL = "http://localhost:8080/manage/tasks";

  @PostMapping("/task")
  public ResponseEntity<TaskDto> saveTask(@RequestBody @Valid TaskDto taskDto)
      throws UserNotFoundException, TodoListNotFoundException {

    Task mappedTask = taskMapper.taskDtoToTask(taskDto);
    Task task = taskService.saveTask(mappedTask);
    TaskDto responseDto = taskMapper.taskToTaskDto(task);

    return ResponseEntity.created(URI.create(BASE_URL)).body(responseDto);
  }

  @GetMapping("/task/{id}")
  public ResponseEntity<TaskDto> getTaskById(
      @PathVariable("id") @Min(value = 1, message = "The id must be greater than 0") Long id)
      throws TaskNotFoundException {

    Task taskById = taskService.getTaskById(id);
    TaskDto responseDto = taskMapper.taskToTaskDto(taskById);

    return ResponseEntity.ok(responseDto);
  }

  @DeleteMapping("/task")
  public ResponseEntity<Void> deleteTaskById(
      @RequestParam("id") @Min(value = 1, message = "The id must be greater than 0") Long id) {

    taskService.deleteTaskById(id);

    return ResponseEntity.noContent().build();
  }

  @PutMapping("/task")
  public ResponseEntity<TaskDto> updateTaskById(
      @RequestParam("id") @Min(value = 1, message = "The id must be greater than 0") Long id,
      @RequestBody @Valid TaskDto updateDto)
      throws TaskNotFoundException {

    Task task = taskMapper.taskDtoToTask(updateDto);
    Task responseTask = taskService.updateTaskById(id, task);
    TaskDto responseDto = taskMapper.taskToTaskDto(responseTask);

    return ResponseEntity.ok(responseDto);
  }

  @GetMapping
  public ResponseEntity<Page<TaskDto>> getTasks(
      @RequestParam(value = "filters", required = false) String[] filters,
      @RequestParam(value = "query", required = false) String query,
      @PageableDefault(
              size = 20,
              sort = {"startFrom"})
          Pageable pageable)
      throws UnsupportedParameterException {

    Page<Task> tasks =
        Objects.isNull(filters) && Objects.isNull(query)
            ? taskService.getTasks(pageable)
            : taskService.getTasksByParams(filters, query, pageable);
    Page<TaskDto> allTasks = tasks.map(taskMapper::taskToTaskDto);

    return ResponseEntity.ok(allTasks);
  }

  @GetMapping("/active")
  public ResponseEntity<Page<TaskDto>> getActiveTasks(
      @PageableDefault(
              size = 20,
              sort = {"startFrom"})
          Pageable pageable) {

    Page<TaskDto> activeTasks = taskService.getActiveTasks(pageable).map(taskMapper::taskToTaskDto);
    return ResponseEntity.ok(activeTasks);
  }

  @GetMapping("/{startFrom}")
  public ResponseEntity<Page<TaskDto>> getTasksFromDate(
      @PathVariable("startFrom")
          @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
          @NotNull
          LocalDateTime dateTime,
      @PageableDefault(
              size = 20,
              sort = {"startFrom"})
          Pageable pageable) {

    Page<TaskDto> tasksFromDate =
        taskService.getTasksFromDate(dateTime, pageable).map(taskMapper::taskToTaskDto);
    return ResponseEntity.ok(tasksFromDate);
  }

  @GetMapping("/missed")
  public ResponseEntity<Page<TaskDto>> getMissedTasks(
      @PageableDefault(
              size = 20,
              sort = {"startFrom"})
          Pageable pageable) {

    Page<TaskDto> missedTasks =
        taskService.getMissedDeadlineTasks(pageable).map(taskMapper::taskToTaskDto);
    return ResponseEntity.ok(missedTasks);
  }

  @GetMapping("/between")
  public ResponseEntity<Page<TaskDto>> getTasksBetween(
      @RequestParam("start")
          @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
          @NotNull
          LocalDateTime start,
      @RequestParam("end")
          @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
          @NotNull
          LocalDateTime end,
      @PageableDefault(
              size = 20,
              sort = {"startFrom"})
          Pageable pageable) {

    Page<TaskDto> tasksBetween =
        taskService.getActiveTasksBetween(start, end, pageable).map(taskMapper::taskToTaskDto);
    return ResponseEntity.ok(tasksBetween);
  }
}

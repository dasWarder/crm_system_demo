package com.example.web.controller.todoList;

import com.example.exception.UserNotFoundException;
import com.example.model.todoList.TodoList;
import com.example.service.todoList.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manage/todo")
public class TodoListController {

  private final TodoListService todoListService;

  private static final String BASE_URL = "http://localhost:8080/manage/todo";

  @PostMapping
  public ResponseEntity<Long> createTodoList() throws UserNotFoundException {

    TodoList todoList = todoListService.createTodoList();

    return ResponseEntity.created(URI.create(BASE_URL)).body(todoList.getUser().getId());
  }
}

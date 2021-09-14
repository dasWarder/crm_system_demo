package com.example.service.todoList;

import com.example.exception.TodoListNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.model.todoList.TodoList;

public interface TodoListService {
  TodoList createTodoList() throws UserNotFoundException;

  TodoList getTodoListById(Long id) throws TodoListNotFoundException;

  void deleteTodoListById(Long id);
}

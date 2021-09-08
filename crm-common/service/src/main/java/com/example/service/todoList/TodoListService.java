package com.example.service.todoList;

import com.example.exception.TodoListNotFoundException;
import com.example.todoList.TodoList;

public interface TodoListService {

    TodoList saveTodoList(TodoList todoList);

    TodoList updateTodoList(Long id, TodoList todoList) throws TodoListNotFoundException;

    TodoList getTodoListById(Long id) throws TodoListNotFoundException;

    void deleteTodoListById(Long id);
}

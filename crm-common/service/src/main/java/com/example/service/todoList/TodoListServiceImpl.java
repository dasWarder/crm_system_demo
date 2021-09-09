package com.example.service.todoList;

import com.example.exception.TodoListNotFoundException;
import com.example.repository.TodoListRepository;
import com.example.model.todoList.TodoList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;

    @Override
    public TodoList saveTodoList(TodoList todoList) {

        log.info("Save a new todo list");
        TodoList storedTodoList = todoListRepository.save(todoList);

        return storedTodoList;
    }

    @Override
    public TodoList updateTodoList(Long id, TodoList todoList) throws TodoListNotFoundException {

        log.info("Update a todo list with id = {}", id);
        TodoList dbTodoList = todoListRepository.findById(id)
                                                .orElseThrow(() -> new TodoListNotFoundException(
                                                String.format("The todo list with id = %d not found", id)));
        todoList.setId(dbTodoList.getId());
        TodoList updatedTodoList = todoListRepository.save(todoList);

        return updatedTodoList;
    }

    @Override
    public TodoList getTodoListById(Long id) throws TodoListNotFoundException {

        log.info("Get a todo list by id = {}", id);
        TodoList todoListById = todoListRepository.findById(id)
                                                    .orElseThrow(() -> new TodoListNotFoundException(
                                                    String.format("The todo list with id = %d not found", id)));

        return todoListById;
    }

    @Override
    public void deleteTodoListById(Long id) {

        log.info("Delete a todo list by id = {}", id);
        todoListRepository.deleteById(id);
    }
}

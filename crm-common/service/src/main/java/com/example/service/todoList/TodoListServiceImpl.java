package com.example.service.todoList;

import com.example.exception.TodoListNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.model.user.User;
import com.example.repository.TodoListRepository;
import com.example.model.todoList.TodoList;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoListServiceImpl implements TodoListService {

  private final UserService userService;

  private final TodoListRepository todoListRepository;

  @Override
  public TodoList createTodoList() throws UserNotFoundException {

    log.info("Save a new todo list");
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String authEmail =
        principal instanceof UserDetails
            ? ((UserDetails) principal).getUsername()
            : principal.toString();

    User loggedUser = userService.getUserByEmail(authEmail);
    TodoList todoList =
        todoListRepository
            .findById(loggedUser.getId())
            .orElse(
                TodoList.builder()
                    .id(loggedUser.getId())
                    .user(loggedUser)
                    .tasks(new ArrayList<>())
                    .build());
    loggedUser.setTodoList(todoList);
    User updatedUser = userService.updateUserByEmail(authEmail, loggedUser);

    return updatedUser.getTodoList();
  }

  @Override
  public TodoList getTodoListById(final Long id) throws TodoListNotFoundException {

    log.info("Get a todo list by id = {}", id);
    TodoList todoListById =
        todoListRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new TodoListNotFoundException(
                        String.format("The todo list with id = %d not found", id)));
    return todoListById;
  }

  @Override
  public void deleteTodoListById(final Long id) {

    log.info("Delete a todo list by id = {}", id);
    todoListRepository.deleteById(id);
  }
}

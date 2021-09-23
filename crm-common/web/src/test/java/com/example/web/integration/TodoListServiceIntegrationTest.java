package com.example.web.integration;

import com.example.exception.TodoListNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.model.todoList.TodoList;
import com.example.service.todoList.TodoListService;
import com.example.web.AbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static com.example.web.data.TestData.WRONG_ID;
import static com.example.web.data.TestTodoListData.TEST_SAVE_TODO;
import static com.example.web.data.TestTodoListData.TEST_TODO_1;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@WithMockUser(username = "test2@gmail.com", authorities = "USER")
@Sql(scripts = {"classpath:/db/todoList/populate_todo_related_tables.sql"})
public class TodoListServiceIntegrationTest extends AbstractTest {

  @Autowired private TodoListService todoListService;

  @Test
  public void shouldCreateTodoListProperly() throws UserNotFoundException {

    log.info("Test createTodoList() method");

    TodoList testTodo = TEST_SAVE_TODO;
    TodoList createdTodo = todoListService.createTodoList();

    assertThat(createdTodo).usingRecursiveComparison().ignoringFields("user").isEqualTo(testTodo);
  }

  @Test
  @WithMockUser(username = "test@gmail.com", authorities = "USER")
  public void shouldReceiveTodoListIfAlreadyExistProperly() throws UserNotFoundException {

    log.info("Test createTodoList() method that should return todo list if it already exists");

    TodoList todoList = todoListService.createTodoList();

    Assertions.assertNotNull(todoList);
    Assertions.assertNotNull(todoList.getTasks());
    Assertions.assertEquals(4, todoList.getTasks().size());
  }

  @Test
  public void shouldGetTodoListByIdProperly() throws TodoListNotFoundException {

    log.info("Test getTodoListById() method");

    TodoList todoListById = todoListService.getTodoListById(TEST_TODO_1.getId());

    Assertions.assertNotNull(todoListById);
    Assertions.assertEquals(TEST_TODO_1.getId(), todoListById.getId());
  }

  @Test
  public void shouldThrowExceptionWhenTodoListIdWrongProperly() {

    log.info("Test getTodoListById() method throws an exception when the ID is wrong");

    Assertions.assertThrows(
        TodoListNotFoundException.class, () -> todoListService.getTodoListById(WRONG_ID));
  }

  @Test
  public void shouldDeleteTodoListByIdProperly() throws TodoListNotFoundException {

    log.info("Test deleteTodoListById() method");

    TodoList todoListById = todoListService.getTodoListById(TEST_TODO_1.getId());
    Assertions.assertNotNull(todoListById);

    todoListService.deleteTodoListById(TEST_TODO_1.getId());
    Assertions.assertThrows(
        TodoListNotFoundException.class,
        () -> todoListService.getTodoListById(TEST_TODO_1.getId()));
  }
}

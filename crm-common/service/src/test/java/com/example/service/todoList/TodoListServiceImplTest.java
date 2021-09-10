package com.example.service.todoList;

import com.example.exception.TodoListNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.model.todoList.TodoList;
import com.example.repository.TodoListRepository;
import com.example.service.user.UserService;
import com.example.service.user.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.example.service.task.TaskTestData.WRONG_ID;
import static com.example.service.todoList.TodoListTestData.TEST_TODO_1;

@Slf4j
class TodoListServiceImplTest {

  private final UserService userService = Mockito.mock(UserServiceImpl.class);

  private final TodoListRepository todoListRepository = Mockito.mock(TodoListRepository.class);

  private final TodoListService todoListService =
      new TodoListServiceImpl(userService, todoListRepository);

  @Test
  public void shouldGetTodoListByIdProperly() throws TodoListNotFoundException {

    log.info("Test getTodoListById() method");
    Mockito.when(todoListRepository.findById(TEST_TODO_1.getId()))
        .thenReturn(Optional.of(TEST_TODO_1));

    TodoList todoListById = todoListService.getTodoListById(TEST_TODO_1.getId());

    Assertions.assertNotNull(todoListById);
    Assertions.assertEquals(TEST_TODO_1, todoListById);
  }

  @Test
  public void shouldThrowExceptionWhenGetTodoListByIdWithNullOrWrongParam() {

    log.info(
        "Test getTodoListById() method throws an exception when id param is null or a wrong one");

    Assertions.assertThrows(
        TodoListNotFoundException.class, () -> todoListService.getTodoListById(null));

    Assertions.assertThrows(
            TodoListNotFoundException.class, () -> todoListService.getTodoListById(WRONG_ID));
  }

  @Test
  public void shouldDeleteTodoListByIdProperly() {

    log.info("Test deleteTodoListById() method");
    todoListService.deleteTodoListById(TEST_TODO_1.getId());
  }
}

package com.example.web.controller.todoList;

import com.example.web.controller.AbstractContextController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WithMockUser(username = "test2@gmail.com", authorities = "ADMIN")
@Sql(scripts = {"classpath:/db/todoList/populate_todo_related_tables.sql"})
public class TodoListControllerTest extends AbstractContextController {

  private static final String BASE_URL = "http://localhost:8080/manage/todo";

  @Test
  public void shouldBeStatusCreatedAndReturnCreatedOrAlreadyExistedTodoListProperly()
      throws Exception {

    log.info("Test createOrGetExistTodoList() method");

    mockMvc.perform(post(BASE_URL)).andDo(print()).andExpect(status().isCreated());
  }
}

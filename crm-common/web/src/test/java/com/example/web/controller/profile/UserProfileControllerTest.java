package com.example.web.controller.profile;

import com.example.mapper.UserMapper;
import com.example.mapper.UserMapperWithAuthority;
import com.example.web.controller.AbstractContextController;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WithMockUser(username = "test@gmail.com", authorities = "USER")
@Sql(
    scripts = {
      "classpath:/db/todoList/populate_todo_related_tables.sql",
      "classpath:/db/contactManager/populate_contacts.sql",
      "classpath:/db/report/populate_report_table.sql"
    })
class UserProfileControllerTest extends AbstractContextController {

  @Autowired private ObjectMapper objectMapper;

  @Autowired private UserMapper userMapper;

  @Autowired private UserMapperWithAuthority customMapper;

  private static final String BASE_URL = "http://localhost:8080/manage/profile";

  @Test
  public void shouldReturnStatusOkAndGetUserProfileProperly() throws Exception {

    log.info("Test getUserProfile() method for /manage/profile endpoint");

    mockMvc
        .perform(get(BASE_URL))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }
}

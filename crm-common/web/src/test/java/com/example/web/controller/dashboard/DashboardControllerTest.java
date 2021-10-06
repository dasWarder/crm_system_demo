package com.example.web.controller.dashboard;

import com.example.web.controller.AbstractContextController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WithMockUser(username = "test@gmail.com", authorities = "USER")
@Sql(
    scripts = {
      "classpath:/db/todoList/populate_todo_related_tables.sql",
      "classpath:/db/report/populate_report_table.sql",
      "classpath:/db/contactManager/populate_contacts_with_users.sql"
    })
class DashboardControllerTest extends AbstractContextController {

  private static final String BASE_URL = "http://localhost:8080/manage/dashboard";

  @Test
  public void shouldShowDashboardProperly() throws Exception {

    log.info("Test showDashboard() method");

    mockMvc
        .perform(get(BASE_URL))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }
}

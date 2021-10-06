package com.example.web.controller.report;

import com.example.mapper.report.ReportMapper;
import com.example.mapper.dto.report.manager.UpdateReportDto;
import com.example.web.controller.AbstractContextController;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static com.example.web.data.TestReportData.TEST_REPORT_1;
import static com.example.web.data.TestReportData.TEST_UPDATE_REPORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WithMockUser(username = "test2@gmail.com", authorities = "MANAGER")
@Sql(
    scripts = {
      "classpath:/db/todoList/populate_todo_related_tables.sql",
      "classpath:/db/report/populate_report_table.sql",
      "classpath:/db/contactManager/populate_contacts_with_users.sql"
    })
class ManagerReportControllerTest extends AbstractContextController {

  @Autowired private ObjectMapper objectMapper;

  @Autowired private ReportMapper reportMapper;

  private static final String BASE_URL = "http://localhost:8080/manage/manager/reports";

  @Test
  public void shouldUpdateUsersReportProperly() throws Exception {

    log.info("Test updateUsersReport() method for PUT /report endpoint");

    UpdateReportDto dto = reportMapper.reportToUpdateReportDto(TEST_UPDATE_REPORT);

    mockMvc
        .perform(
            put(BASE_URL + "/report")
                .param("id", String.valueOf(TEST_UPDATE_REPORT.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldGetReportDetailsProperly() throws Exception {

    log.info("Test getReportDetails() method for GET /report/{id} endpoint");

    mockMvc
        .perform(get(BASE_URL + "/report/" + TEST_REPORT_1.getId()))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldDeleteReportByIdProperly() throws Exception {

    log.info("Test deleteReportById() method for DELETE /report endpoint");

    mockMvc
        .perform(delete(BASE_URL + "/report").param("id", String.valueOf(TEST_REPORT_1.getId())))
        .andDo(print())
        .andExpect(status().isNoContent())
        .andReturn();
  }

  @Test
  public void shouldGetAllReportsProperly() throws Exception {

    log.info("Test getAllReports() method");

    mockMvc
        .perform(get(BASE_URL))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldGetTodayReportsProperly() throws Exception {

    log.info("Test getTodayReports() method for GET /today endpoint");

    mockMvc
        .perform(get(BASE_URL + "/today"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldGetReportsByDateProperly() throws Exception {

    log.info("Test getReportsByDate() method for GET /{date} endpoint");

    mockMvc
        .perform(get(BASE_URL))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }
}

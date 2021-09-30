package com.example.web.controller.report;

import com.example.mapper.ReportMapper;
import com.example.mapper.dto.report.CreateReportDto;
import com.example.mapper.dto.report.ResponseReportDto;
import com.example.model.report.Report;
import com.example.model.report.ReportTopic;
import com.example.web.controller.AbstractContextController;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static com.example.model.report.ReportStatus.RECEIVED;
import static com.example.model.report.ReportTopic.VACATION;
import static com.example.web.data.TestReportData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WithMockUser(username = "test@gmail.com", authorities = "USER")
@Sql(
    scripts = {
      "classpath:/db/todoList/populate_todo_related_tables.sql",
      "classpath:/db/report/populate_report_table.sql"
    })
class UserReportControllerTest extends AbstractContextController {

  @Autowired private ObjectMapper objectMapper;

  @Autowired private ReportMapper reportMapper;

  private static final String BASE_URL = "http://localhost:8080/manage/reports";

  @Test
  public void shouldCreateNewReportProperly() throws Exception {

    log.info("Test createNewReport() method for  POST /report endpoint");

    Report saveReport = TEST_SAVE_REPORT;
    saveReport.setStatus(RECEIVED);
    CreateReportDto dto = reportMapper.reportToCreateReportDto(saveReport);

    mockMvc
        .perform(
            post(BASE_URL + "/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andReturn();
  }

  @Test
  public void shouldGetDetailsReportInfoByIdProperly() throws Exception {

    log.info("Test getDetailsReportInfoById() method for GET /report/{id} endpoint");

    mockMvc
        .perform(get(BASE_URL + "/report/" + TEST_REPORT_1.getId()))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldUpdateReportProperly() throws Exception {

    log.info("Test updateReport() method for PUT /report");

    CreateReportDto dto = new CreateReportDto(ReportTopic.LEAVE, "Leave form date to date");

    mockMvc
        .perform(
            put(BASE_URL + "/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
                .param("id", String.valueOf(TEST_REPORT_2.getId())))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldDeleteReportByIdProperly() throws Exception {

    log.info("Test deleteReportById() method for DELETE /report endpoint");

    mockMvc
        .perform(delete(BASE_URL + "/report").param("id", String.valueOf(TEST_REPORT_3.getId())))
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
  public void shouldGetReportsByTopicProperly() throws Exception {

    log.info("Test getReportsByTopic() method for GET /topic endpoint");

    mockMvc
        .perform(get(BASE_URL + "/topic").param("topic", VACATION.toString()))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldGetReportsByStatusProperly() throws Exception {

    log.info("Test getReportsByStatus() method for GET /status endpoint");

    mockMvc
        .perform(get(BASE_URL + "/status").param("status", RECEIVED.toString()))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }
}

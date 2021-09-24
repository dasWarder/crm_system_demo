package com.example.web.integration;

import com.example.exception.ReportNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.model.report.Report;
import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;
import com.example.service.report.UserReportService;
import com.example.web.AbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.web.data.TestData.TEST_PAGEABLE;
import static com.example.web.data.TestReportData.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@WithMockUser(username = "test@gmail.com", authorities = "USER")
@Sql(
    scripts = {
      "classpath:/db/todoList/populate_todo_related_tables.sql",
      "classpath:/db/report/populate_report_table.sql"
    })
public class UserReportServiceIntegrationTest extends AbstractTest {

  @Autowired private UserReportService reportService;

  @Test
  public void shouldCreateReportProperly() throws UserNotFoundException {

    log.info("Test createReport() method");

    Report report = reportService.createReport(TEST_SAVE_REPORT);

    Assert.assertNotNull(report);
    assertThat(report)
        .usingRecursiveComparison()
        .ignoringFields("user")
        .isEqualTo(TEST_SAVE_REPORT);
  }

  @Test
  public void shouldUpdateReportProperly() throws UserNotFoundException, ReportNotFoundException {

    log.info("Test updateReport() method");
    Report updatedReport = reportService.updateReport(TEST_REPORT_2.getId(), TEST_UPDATE_REPORT);

    Assert.assertNotNull(updatedReport);
    assertThat(updatedReport)
        .usingRecursiveComparison()
        .ignoringFields("user")
        .isEqualTo(TEST_UPDATE_REPORT);
  }

  @Test
  public void shouldGetReportByIdProperly() throws ReportNotFoundException {

    log.info("Test getReportById() method");
    Report reportById = reportService.getReportById(TEST_REPORT_1.getId());

    Assert.assertNotNull(reportById);
    assertThat(reportById)
        .usingRecursiveComparison()
        .ignoringFields("user")
        .isEqualTo(TEST_REPORT_1);
  }

  @Test
  public void shouldDeleteReportByIdProperly() throws ReportNotFoundException {

    log.info("Test deleteReportById() method");

    Report reportById = reportService.getReportById(TEST_REPORT_1.getId());
    Assert.assertNotNull(reportById);

    reportService.deleteReportById(TEST_REPORT_1.getId());
    Assert.assertThrows(
        ReportNotFoundException.class, () -> reportService.getReportById(TEST_REPORT_1.getId()));
  }

  @Test
  public void shouldGetReportsForCurrentUserProperly() throws UserNotFoundException {

    log.info("Test getReportsForCurrentUser() method");
    Page<Report> reports = reportService.getReportsForCurrentUser(TEST_PAGEABLE);
    List<Report> expectedReports =
            Stream.of(TEST_REPORT_1, TEST_REPORT_2, TEST_REPORT_3).collect(Collectors.toList());

    Assert.assertNotNull(reports);
    Assert.assertEquals(expectedReports.size(), reports.getContent().size());
  }

  @Test
  public void shouldGetReportsByTopicForCurrentUser() throws UserNotFoundException {

    log.info("Test getReportsByTopicForCurrentUser() method");
    Page<Report> reports =
        reportService.getReportsByTopicForCurrentUser(ReportTopic.VACATION, TEST_PAGEABLE);
    List<Report> expectedReports =
            Stream.of(TEST_REPORT_1).collect(Collectors.toList());

    Assert.assertNotNull(reports);
    Assert.assertEquals(expectedReports.size(), reports.getContent().size());
  }

  @Test
  public void shouldGetReportsByStatusForCurrentUserProperly() throws UserNotFoundException {

    log.info("Test getReportsByStatusForCurrentUser() method");
    Page<Report> reports =
        reportService.getReportsByStatusForCurrentUser(ReportStatus.RECEIVED, TEST_PAGEABLE);
    List<Report> expectedReports =
            Stream.of(TEST_REPORT_1, TEST_REPORT_2).collect(Collectors.toList());

    Assert.assertNotNull(reports);
    Assert.assertEquals(expectedReports.size(), reports.getContent().size());
  }

  @Test
  public void shouldGetLastCurrentUserReportsProperly() throws UserNotFoundException {

    log.info("Test getLastCurrentUserReports() method");
    List<Report> reports = reportService.getLastCurrentUserReports(TEST_PAGEABLE);
    List<Report> expectedReports =
        Stream.of(TEST_REPORT_1, TEST_REPORT_2, TEST_REPORT_3).collect(Collectors.toList());

    Assert.assertNotNull(reports);
    Assert.assertEquals(expectedReports.size(), reports.size());
  }
}

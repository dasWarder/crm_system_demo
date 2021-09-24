package com.example.web.integration;

import com.example.exception.ReportNotFoundException;
import com.example.model.report.Report;
import com.example.model.report.ReportStatus;
import com.example.service.report.ManagerReportService;
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
public class ManagerReportServiceIntegrationTest extends AbstractTest {

  @Autowired private ManagerReportService reportService;

  @Test
  public void shouldUpdateUsersReportProperly() {

    log.info("Test updateUsersReport() method");

    Report report = reportService.updateUsersReport(TEST_UPDATE_REPORT);

    Assert.assertNotNull(report);
    assertThat(report)
        .usingRecursiveComparison()
        .ignoringFields("user")
        .isEqualTo(TEST_UPDATE_REPORT);
  }

  @Test
  public void shouldGetUsersReportByIdProperly() throws ReportNotFoundException {

    log.info("Test getUsersReportById() method");

    Report report = reportService.getUsersReportById(TEST_REPORT_1.getId());

    Assert.assertNotNull(report);
    assertThat(report).usingRecursiveComparison().ignoringFields("user").isEqualTo(TEST_REPORT_1);
  }

  @Test
  public void shouldDeleteReportByIdProperly() throws ReportNotFoundException {

    log.info("Test deleteReportById() method");

    Report report = reportService.getUsersReportById(TEST_REPORT_2.getId());
    Assert.assertNotNull(report);

    reportService.deleteReportById(TEST_REPORT_2.getId());
    Assert.assertThrows(
        ReportNotFoundException.class,
        () -> reportService.getUsersReportById(TEST_REPORT_2.getId()));
  }

  @Test
  public void shouldDeleteReportsByStatusProperly() throws ReportNotFoundException {

    log.info("Test deleteReportsByStatus() method");
    Report firstReport = reportService.getUsersReportById(TEST_REPORT_1.getId());
    Report secondReport = reportService.getUsersReportById(TEST_REPORT_2.getId());
    Assert.assertNotNull(firstReport);
    Assert.assertNotNull(secondReport);

    reportService.deleteReportsByStatus(ReportStatus.RECEIVED);
    Assert.assertThrows(
        ReportNotFoundException.class,
        () -> reportService.getUsersReportById(TEST_REPORT_1.getId()));
    Assert.assertThrows(
        ReportNotFoundException.class,
        () -> reportService.getUsersReportById(TEST_REPORT_2.getId()));
  }

  @Test
  public void shouldGetAllReportsProperly() {

    log.info("Test getAllReports() method");

    Page<Report> allReports = reportService.getAllReports(TEST_PAGEABLE);
    List<Report> expectedReports =
        Stream.of(TEST_REPORT_1, TEST_REPORT_2, TEST_REPORT_3).collect(Collectors.toList());

    Assert.assertNotNull(allReports);
    Assert.assertEquals(expectedReports.size(), allReports.getContent().size());
  }

  @Test
  public void shouldGetTodayReportsProperly() {

    log.info("Test getTodayReports() method");

    Page<Report> todayReports = reportService.getTodayReports(TEST_PAGEABLE);

    Assert.assertNotNull(todayReports);
    Assert.assertEquals(0, todayReports.getContent().size());
  }

  @Test
  public void shouldGetReportsByCreatedAtProperly() {

    log.info("Test getReportsByCreatedAt() method");

    Page<Report> reportsByCreatedAt = reportService.getReportsByCreatedAt(TEST_REPORT_1.getCreatedAt(), TEST_PAGEABLE);
    List<Report> expectedReports = Stream.of(TEST_REPORT_1, TEST_REPORT_2).collect(Collectors.toList());

    Assert.assertNotNull(reportsByCreatedAt);
    Assert.assertEquals(expectedReports.size(), reportsByCreatedAt.getContent().size());
  }

  @Test
  public void shouldGetAlreadyFinishedReportsProperly() {

    log.info("Test getAlreadyFinishedReports() method");

    List<Report> expectedReports = Stream.of(TEST_REPORT_3).collect(Collectors.toList());
    Page<Report> alreadyFinishedReports = reportService.getAlreadyFinishedReports(TEST_PAGEABLE);

    Assert.assertNotNull(alreadyFinishedReports);
    Assert.assertEquals(expectedReports.size(), alreadyFinishedReports.getContent().size());
  }
}

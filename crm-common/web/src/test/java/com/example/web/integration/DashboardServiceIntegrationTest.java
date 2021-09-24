package com.example.web.integration;

import com.example.exception.UserNotFoundException;
import com.example.service.dashboard.DashboardService;
import com.example.web.AbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.web.data.TestReportData.*;
import static com.example.web.data.TestTaskData.TEST_TASK_4;

@Slf4j
@WithMockUser(username = "test@gmail.com", authorities = "USER")
@Sql(
    scripts = {
      "classpath:/db/todoList/populate_todo_related_tables.sql",
      "classpath:/db/report/populate_report_table.sql"
    })
public class DashboardServiceIntegrationTest extends AbstractTest {

  @Autowired private DashboardService dashboardService;

  @Test
  public void shouldGetNumberOfReportsProperly() throws UserNotFoundException {

    log.info("Test getNumberOfReports() method");

    Long expectedCount =
        (long)
            Stream.of(TEST_REPORT_1, TEST_REPORT_2, TEST_REPORT_3)
                .collect(Collectors.toList())
                .size();
    Long numberOfReports = dashboardService.getNumberOfReports();

    Assert.assertNotNull(numberOfReports);
    Assert.assertEquals(expectedCount, numberOfReports);
  }

  @Test
  public void shouldGetNumberOfDaysInCompanyProperly() throws UserNotFoundException {

    log.info("Test getNumberOfDaysInCompany() method");
    Long numberOfDaysInCompany = dashboardService.getNumberOfDaysInCompany();

    Assert.assertNotNull(numberOfDaysInCompany);
  }

  @Test
  public void shouldGetNumberOfActiveTasksProperly() throws UserNotFoundException {

    log.info("Test getNumberOfActiveTasks() method");

    Long actualCount = (long) Stream.of(TEST_TASK_4).collect(Collectors.toList()).size();
    Long activeTasksCount = dashboardService.getNumberOfActiveTasks();

    Assert.assertNotNull(activeTasksCount);
    Assert.assertEquals(actualCount, activeTasksCount);
  }
}

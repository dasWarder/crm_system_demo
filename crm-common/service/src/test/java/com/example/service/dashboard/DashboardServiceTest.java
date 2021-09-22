package com.example.service.dashboard;

import com.example.exception.UserNotFoundException;
import com.example.model.report.Report;
import com.example.model.todoList.Task;
import com.example.service.report.UserReportService;
import com.example.service.task.TaskService;
import com.example.service.task.TaskTestData;
import com.example.service.user.UserService;
import com.example.service.util.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.example.service.report.ReportTestData.*;
import static com.example.service.task.TaskTestData.*;
import static com.example.service.user.UserTestData.TEST_USER_1;
import static com.example.service.util.TestUtil.getListOfObjects;

@Slf4j
class DashboardServiceTest {

  private final TaskService taskService = Mockito.mock(TaskService.class);

  private final UserService userService = Mockito.mock(UserService.class);

  private final UserReportService reportService = Mockito.mock(UserReportService.class);

  private static final Pageable TEST_PAGEABLE = PageRequest.of(0, 20);

  private final DashboardService dashboardService =
      new DashboardServiceImpl(taskService, userService, reportService);

  @Test
  public void shouldGetNumberOfReportsProperly() throws UserNotFoundException {

    log.info("Test getNumberOfReports() method");

    List<Report> reports = getListOfObjects(TEST_REPORT_1, TEST_REPORT_2, TEST_REPORT_3);
    PageImpl<Report> reportsPage = new PageImpl<>(reports);

    Mockito.when(reportService.getReportsForCurrentUser(TEST_PAGEABLE)).thenReturn(reportsPage);

    Long numberOfReports = dashboardService.getNumberOfReports();

    Assertions.assertNotNull(numberOfReports);
    Assertions.assertEquals(reports.size(), numberOfReports);
  }

  @Test
  public void shouldGetNumberOfDaysInCompanyProperly() throws UserNotFoundException {

    log.info("Test getNumberOfDaysInCompany() method");
    Mockito.when(userService.getCurrentUser()).thenReturn(TEST_USER_1);

    LocalDate registrationDate = TEST_USER_1.getRegistrationDate();
    LocalDate currentDate = LocalDate.now();
    long daysBetween = ChronoUnit.DAYS.between(registrationDate, currentDate);

    Long numberOfDaysInCompany = dashboardService.getNumberOfDaysInCompany();

    Assertions.assertNotNull(numberOfDaysInCompany);
    Assertions.assertEquals(daysBetween, numberOfDaysInCompany);
  }

  @Test
  public void shouldGetNumberOfActiveTasksProperly() throws UserNotFoundException {

    log.info("Test getNumberOfActiveTasks() method");

    List<Task> tasks = getListOfObjects(TEST_TASK_1, TEST_TASK_2, TEST_TASK_3);
    PageImpl<Task> tasksPage = new PageImpl<>(tasks);
    Mockito.when(taskService.getActiveTasks(TEST_PAGEABLE)).thenReturn(tasksPage);

    Long numberOfActiveTasks = dashboardService.getNumberOfActiveTasks();

    Assertions.assertNotNull(numberOfActiveTasks);
    Assertions.assertEquals(tasks.size(), numberOfActiveTasks);
  }
}

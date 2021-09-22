package com.example.service.dashboard;

import com.example.service.report.UserReportService;
import com.example.service.task.TaskService;
import com.example.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Slf4j
class DashboardServiceTest {

  private final TaskService taskService = Mockito.mock(TaskService.class);

  private final UserService userService = Mockito.mock(UserService.class);

  private final UserReportService reportService = Mockito.mock(UserReportService.class);

  private static final Pageable TEST_PAGEABLE = PageRequest.of(0, 20);

  private final DashboardService dashboardService =
      new DashboardServiceImpl(taskService, userService, reportService);


  public void shouldGetNumberOfReportsProperly() {
      /*
      log.info("Get number of reports for a current user");
    List<Report> listOfUserReports =
        reportService.getReportsForCurrentUser(TEST_PAGEABLE).getContent();
    Long numOfReports = (long) listOfUserReports.size();

    return numOfReports;
       */
      log.info("Test getNumberOfReports() method");


  }
}

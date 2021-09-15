package com.example.web.controller.dashboard;

import com.example.exception.UserNotFoundException;
import com.example.mapper.DashboardMapper;
import com.example.mapper.dto.dashboard.DashboardDto;
import com.example.model.report.Report;
import com.example.model.todoList.Task;
import com.example.service.dashboard.DashboardService;
import com.example.service.report.UserReportService;
import com.example.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manage/dashboard")
public class DashboardController {

  private final DashboardMapper mapper;

  private final TaskService taskService;

  private final DashboardService dashboardService;

  private final UserReportService userReportService;

  @GetMapping
  public ResponseEntity<DashboardDto> showDashboard(@PageableDefault(size = 20) Pageable pageable)
      throws UserNotFoundException {
    Long activeTasks = dashboardService.getNumberOfActiveTasks();
    Long daysInCompany = dashboardService.getNumberOfDaysInCompany();
    Long reports = dashboardService.getNumberOfReports();
    List<Task> lastActiveTasks = taskService.getLastActiveTasks(pageable);
    List<Report> lastReports = userReportService.getLastCurrentUserReports(pageable);

    DashboardDto dashboardDto =
        mapper.toDashboardDto(daysInCompany, reports, lastReports, activeTasks, lastActiveTasks);

    return ResponseEntity.ok(dashboardDto);
  }
}

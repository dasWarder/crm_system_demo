package com.example.service.dashboard;

import com.example.exception.UserNotFoundException;
import com.example.model.report.Report;
import com.example.model.todoList.Task;
import com.example.model.todoList.TodoList;
import com.example.model.user.User;
import com.example.service.report.UserReportService;
import com.example.service.task.TaskService;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

  private final TaskService taskService;

  private final UserService userService;

  private final UserReportService reportService;

  private static final Pageable TEST_PAGEABLE = PageRequest.of(0, 20);

  @Override
  public Long getNumberOfReports() throws UserNotFoundException {

    log.info("Get number of reports for a current user");
    List<Report> listOfUserReports =
        reportService.getReportsForCurrentUser(TEST_PAGEABLE).getContent();
    Long numOfReports = (long) listOfUserReports.size();

    return numOfReports;
  }

  @Override
  public Long getNumberOfDaysInCompany() throws UserNotFoundException {

    log.info("Get number of a current user days from registration");
    User currentUser = getCurrentUser();
    LocalDate registrationDate = currentUser.getRegistrationDate();
    LocalDate currentDate = LocalDate.now();

    long daysBetween = ChronoUnit.DAYS.between(registrationDate, currentDate);

    return daysBetween;
  }

  @Override
  public Long getNumberOfActiveTasks() {

    log.info("Get number of active tasks for a current user");
    List<Task> activeTasks = taskService.getActiveTasks(TEST_PAGEABLE).getContent();
    Long numOfActiveTasks = (long) activeTasks.size();

    return numOfActiveTasks;
  }

  private User getCurrentUser() throws UserNotFoundException {

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String authEmail =
        principal instanceof UserDetails
            ? ((UserDetails) principal).getUsername()
            : principal.toString();

    User userByEmail = userService.getUserByEmail(authEmail);

    return userByEmail;
  }
}

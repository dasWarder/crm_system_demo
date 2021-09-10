package com.example.service.report;

import com.example.exception.ReportNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.model.report.Report;
import com.example.model.report.ReportStatus;
import com.example.model.user.User;
import com.example.repository.ReportRepository;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

  private final UserService userService;

  private final ReportRepository reportRepository;

  @Override
  public Report createReport(Report report) throws UserNotFoundException {

    log.info("Create a new report");

    User currentUser = getCurrentUser();
    report.setUser(currentUser);
    report.setReportStatus(ReportStatus.RECEIVED);
    Report storedReport = reportRepository.save(report);

    return storedReport;
  }

  @Override
  public Report updateReport(final Long id, Report report) throws ReportNotFoundException {

    log.info("Update a report with id = {}", id);
    Report originalReport =
        reportRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ReportNotFoundException(
                        String.format("The report with id = %d not found", id)));
    report.setId(originalReport.getId());
    report.setUser(originalReport.getUser());
    Report updatedReport = reportRepository.save(report);

    return updatedReport;
  }

  @Override
  public Report getReportById(final Long id) throws ReportNotFoundException {

    log.info("Get a report by id = {}", id);
    Report reportById =
        reportRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ReportNotFoundException(
                        String.format("The report with id = %d not found", id)));
    
    return reportById;
  }

  @Override
  public void deleteReportById(final Long id) {

    log.info("Delete a report by id = {}", id);
    reportRepository.deleteById(id);
  }

  @Override
  public Page<Report> getReportsForCurrentUser(Pageable pageable) throws UserNotFoundException {

    User currentUser = getCurrentUser();
    log.info("Get reports for a current user with id = {}", currentUser.getId());
    Page<Report> currentUserReports =
        reportRepository.getReportsByUser_Email(currentUser.getEmail(), pageable);

    return currentUserReports;
  }

  @Override
  public Page<Report> getReportsByTopicForCurrentUser(String topic, Pageable pageable)
      throws UserNotFoundException {

    User currentUser = getCurrentUser();
    log.info("Get reports by a topic for a user with id = {}", currentUser.getId());
    Page<Report> currentUserReportsByTopic =
        reportRepository.getReportsByReportTopicAndUser_Email(
            topic, currentUser.getEmail(), pageable);

    return currentUserReportsByTopic;
  }

  @Override
  public Page<Report> getReportsByStatusForCurrentUser(String status, Pageable pageable)
      throws UserNotFoundException {

    User currentUser = getCurrentUser();
    log.info("Get reports by a status for a user with id = {}", currentUser.getId());
    Page<Report> currentUserReportsByStatus =
        reportRepository.getReportsByReportStatusAndUser_Email(
            status, currentUser.getEmail(), pageable);

    return currentUserReportsByStatus;
  }

  @Override
  public Page<Report> getAllReports(Pageable pageable) {

    log.info("Get reports of all users");
    Page<Report> allReports = reportRepository.findAll(pageable);

    return allReports;
  }

  @Override
  public Page<Report> getReportsByCreatedAt(LocalDateTime createdAt, Pageable pageable) {

    log.info("Get all report by creating at {}", createdAt.toString());
    Page<Report> reportsByCreatedAt = reportRepository.getReportsByCreatedAt(createdAt, pageable);

    return reportsByCreatedAt;
  }

  @Override
  public Page<Report> getReportsByStatus(String status, Pageable pageable) {

    log.info("Get all reports by a status = {}", status);
    Page<Report> reportsByReportStatus =
        reportRepository.getReportsByReportStatus(status, pageable);

    return reportsByReportStatus;
  }

  private User getCurrentUser() throws UserNotFoundException {

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String authEmail =
        principal instanceof UserDetails
            ? ((UserDetails) principal).getUsername()
            : principal.toString();

    User loggedUser = userService.getUserByEmail(authEmail);

    return loggedUser;
  }
}

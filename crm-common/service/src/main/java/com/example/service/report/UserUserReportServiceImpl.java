package com.example.service.report;

import com.example.exception.ReportNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.model.report.Report;
import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;
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
public class UserUserReportServiceImpl implements UserReportService {

  private final UserService userService;

  private final ReportRepository reportRepository;

  @Override
  public Report createReport(Report report) throws UserNotFoundException {

    log.info("Create a new report");

    User currentUser = getCurrentUser();
    report.setUser(currentUser);
    report.setStatus(ReportStatus.RECEIVED);
    report.setCreatedAt(LocalDateTime.now());
    report.setStatusChanged(LocalDateTime.now());
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
    report.setCreatedAt(LocalDateTime.now());
    report.setStatusChanged(LocalDateTime.now());
    report.setStatus(ReportStatus.RECEIVED);
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
  public Page<Report> getReportsForCurrentUser(final Pageable pageable)
      throws UserNotFoundException {

    User currentUser = getCurrentUser();
    log.info("Get reports for a current user with id = {}", currentUser.getId());
    Page<Report> currentUserReports =
        reportRepository.getReportsByUser_Email(currentUser.getEmail(), pageable);

    return currentUserReports;
  }

  @Override
  public Page<Report> getReportsByTopicForCurrentUser(
      final ReportTopic topic, final Pageable pageable) throws UserNotFoundException {

    User currentUser = getCurrentUser();
    log.info("Get reports by a topic for a user with id = {}", currentUser.getId());
    Page<Report> currentUserReportsByTopic =
        reportRepository.getReportsByTopicAndUser_Email(
            topic, currentUser.getEmail(), pageable);

    return currentUserReportsByTopic;
  }

  @Override
  public Page<Report> getReportsByStatusForCurrentUser(
      final ReportStatus status, final Pageable pageable) throws UserNotFoundException {

    User currentUser = getCurrentUser();
    log.info("Get reports by a status for a user with id = {}", currentUser.getId());
    Page<Report> currentUserReportsByStatus =
        reportRepository.getReportsByStatusAndUser_Email(
            status, currentUser.getEmail(), pageable);

    return currentUserReportsByStatus;
  }

//  @Override
//  public Page<Report> getAllReports(final Pageable pageable) {
//
//    log.info("Get reports of all users");
//    Page<Report> allReports = reportRepository.findAll(pageable);
//
//    return allReports;
//  }
//
//  @Override
//  public Page<Report> getReportsByCreatedAt(
//      final LocalDateTime createdAt, final Pageable pageable) {
//
//    log.info("Get all report by creating at {}", createdAt.toString());
//    Page<Report> reportsByCreatedAt = reportRepository.getReportsByCreatedAt(createdAt, pageable);
//
//    return reportsByCreatedAt;
//  }
//
//  @Override
//  public Page<Report> getReportsByStatus(final ReportStatus status, final Pageable pageable) {
//
//    log.info("Get all reports by a status = {}", status);
//    Page<Report> reportsByReportStatus =
//        reportRepository.getReportsByStatus(status, pageable);
//
//    return reportsByReportStatus;
//  }

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

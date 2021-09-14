package com.example.service.report;

import com.example.exception.ReportNotFoundException;
import com.example.model.report.Report;
import com.example.model.report.ReportStatus;
import com.example.repository.ReportRepository;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerReportServiceImpl implements ManagerReportService {

  private final ReportRepository reportRepository;

  @Override
  public Report updateUsersReport(Long id, Report updateReport) throws ReportNotFoundException {

    log.info("Update the user's report with id = {}", id);
    Report dbReport =
        reportRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ReportNotFoundException(
                        String.format("The report with id = %d not found", id)));
    updateReport.setId(dbReport.getId());
    updateReport.setUser(dbReport.getUser());
    updateReport.setStatusChanged(LocalDateTime.now());

    Report updatedReport = reportRepository.save(updateReport);

    return updatedReport;
  }

  @Override
  public Report getUsersReportById(Long id) throws ReportNotFoundException {

    log.info("Get the report with id = {}", id);
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
  public void deleteReportById(Long id) {

    log.info("Delete the report by id = {}", id);
    reportRepository.deleteById(id);
  }

  @Override
  public void deleteReportsByStatus(ReportStatus status) {

    log.info("Delete reports with a status = {}", status.toString());
    reportRepository.deleteReportsByStatus(status);
  }

  @Override
  public Page<Report> getAllReports(Pageable pageable) {

    log.info("Get all user's reports");
    Page<Report> reports = reportRepository.findAll(pageable);

    return reports;
  }

  @Override
  public Page<Report> getReportsByCreatedAt(LocalDateTime createdAt, Pageable pageable) {

    log.info("Get all user's reports by creating date = {}", createdAt.toString());
    Page<Report> reportsByCreatedAt = reportRepository.getReportsByCreatedAt(createdAt, pageable);

    return reportsByCreatedAt;
  }

  @Override
  public Page<Report> getReportsByStatus(ReportStatus status, Pageable pageable) {

    log.info("Get all user's reports by a status = {}", status.toString());
    Page<Report> reportsByStatus = reportRepository.getReportsByStatus(status, pageable);

    return reportsByStatus;
  }
}

package com.example.service.report;

import com.example.exception.ReportNotFoundException;
import com.example.model.report.Report;
import com.example.model.report.ReportStatus;
import com.example.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerReportServiceImpl implements ManagerReportService {

  private final ReportRepository reportRepository;

  @Override
  public Report updateUsersReport(Report updateReport) {

    log.info("Update the user's report with id = {}", updateReport.getId());
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
    Page<Report> reportsByCreatedAt = reportRepository.getReportsByCreatedAtAfter(createdAt, pageable);

    return reportsByCreatedAt;
  }

  @Override
  public Page<Report> getTodayReports(Pageable pageable) {

    log.info("Get all user's reports for today");
    LocalDateTime currentDateTime = LocalDateTime.now();
    LocalDateTime today =
        LocalDateTime.of(
            currentDateTime.getYear(),
            currentDateTime.getMonth(),
            currentDateTime.getDayOfMonth(),
            00,
            00,
            00);
    Page<Report> reportsByCreatedAt =
        reportRepository.getReportsByCreatedAtAfter(today, pageable);

    return reportsByCreatedAt;
  }

  @Override
  public Page<Report> getAlreadyFinishedReports(Pageable pageable) {

    log.info("Get all user's approved reports");
    List<Report> reportsByStatus =
        reportRepository.getReportsByStatus(ReportStatus.APPROVED, pageable).getContent();
    List<Report> deniedReports =
        reportRepository.getReportsByStatus(ReportStatus.DENIED, pageable).getContent();
    List<Report> reports =
        Stream.of(reportsByStatus, deniedReports)
            .flatMap(list -> list.stream())
            .collect(Collectors.toList());

    PageImpl<Report> finishedReports = new PageImpl<>(reports, pageable, reports.size());

    return finishedReports;
  }
}

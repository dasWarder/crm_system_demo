package com.example.service.report;

import com.example.exception.ReportNotFoundException;
import com.example.model.report.Report;
import com.example.model.report.ReportStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface ManagerReportService {

  Report updateUsersReport(final Report updateReport);

  Report getUsersReportById(final Long id) throws ReportNotFoundException;

  void deleteReportById(final Long id);

  void deleteReportsByStatus(ReportStatus status);

  Page<Report> getAllReports(final Pageable pageable);

  Page<Report> getTodayReports(final Pageable pageable);

  Page<Report> getReportsByCreatedAt(final LocalDateTime createdAt, final Pageable pageable);

  Page<Report> getAlreadyFinishedReports(final Pageable pageable);
}

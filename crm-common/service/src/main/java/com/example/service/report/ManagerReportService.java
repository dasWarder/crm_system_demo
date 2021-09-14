package com.example.service.report;

import com.example.exception.ReportNotFoundException;
import com.example.model.report.Report;
import com.example.model.report.ReportStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface ManagerReportService {

  Report updateUsersReport(Long id, Report updateReport) throws ReportNotFoundException;

  Report getUsersReportById(Long id) throws ReportNotFoundException;

  void deleteReportById(Long id);

  void deleteReportsByStatus(ReportStatus status);

  Page<Report> getAllReports(final Pageable pageable);

  Page<Report> getReportsByCreatedAt(final LocalDateTime createdAt, final Pageable pageable);

  Page<Report> getReportsByStatus(final ReportStatus status, final Pageable pageable);
}

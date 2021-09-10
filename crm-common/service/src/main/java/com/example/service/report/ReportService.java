package com.example.service.report;

/*

   Page<Report> getReportsByCreatedAt(LocalDateTime createdAt);

   Page<Report> getReportsByReportStatus(String status);
*/

import com.example.exception.ReportNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.model.report.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface ReportService {

  Report createReport(Report report) throws UserNotFoundException;

  Report updateReport(final Long id, Report report) throws UserNotFoundException, ReportNotFoundException;

  Report getReportById(final Long id) throws ReportNotFoundException;

  void deleteReportById(final Long id);

  Page<Report> getReportsForCurrentUser(Pageable pageable) throws UserNotFoundException;

  Page<Report> getReportsByTopicForCurrentUser(String topic, Pageable pageable) throws UserNotFoundException;

  Page<Report> getReportsByStatusForCurrentUser(String status, Pageable pageable) throws UserNotFoundException;

  Page<Report> getAllReports(Pageable pageable);

  Page<Report> getReportsByCreatedAt(LocalDateTime createdAt, Pageable pageable);

  Page<Report> getReportsByStatus(String status, Pageable pageable);
}

package com.example.service.report;

/*

   Page<Report> getReportsByCreatedAt(LocalDateTime createdAt);

   Page<Report> getReportsByReportStatus(String status);
*/

import com.example.exception.ReportNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.model.report.Report;
import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface UserReportService {

  Report createReport(Report report) throws UserNotFoundException;

  Report updateReport(final Long id, Report report) throws UserNotFoundException, ReportNotFoundException;

  Report getReportById(final Long id) throws ReportNotFoundException;

  void deleteReportById(final Long id);

  Page<Report> getReportsForCurrentUser(final Pageable pageable) throws UserNotFoundException;

  Page<Report> getReportsByTopicForCurrentUser(final ReportTopic topic, final Pageable pageable) throws UserNotFoundException;

  Page<Report> getReportsByStatusForCurrentUser(final ReportStatus status, final Pageable pageable) throws UserNotFoundException;
}

package com.example.service.report;

import com.example.exception.ReportNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.model.report.Report;
import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserReportService {

  Report createReport(Report report) throws UserNotFoundException;

  Report updateReport(final Long id, Report report) throws UserNotFoundException, ReportNotFoundException;

  Report getReportById(final Long id) throws ReportNotFoundException;

  void deleteReportById(final Long id);

  Page<Report> getReportsForCurrentUser(final Pageable pageable) throws UserNotFoundException;

  Page<Report> getReportsByTopicForCurrentUser(final ReportTopic topic, final Pageable pageable) throws UserNotFoundException;

  Page<Report> getReportsByStatusForCurrentUser(final ReportStatus status, final Pageable pageable) throws UserNotFoundException;

  List<Report> getLastCurrentUserReports(final Pageable pageable) throws UserNotFoundException;
}

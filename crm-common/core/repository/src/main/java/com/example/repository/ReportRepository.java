package com.example.repository;

import com.example.model.report.Report;
import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;

public interface ReportRepository extends PagingAndSortingRepository<Report, Long> {

  Page<Report> getReportsByTopicAndUser_Email(ReportTopic topic, String email, Pageable pageable);

  Page<Report> getReportsByUser_Email(String email, Pageable pageable);

  Page<Report> getReportsByStatusAndUser_Email(
          ReportStatus status, String email, Pageable pageable);

  @Override
  Page<Report> findAll(Pageable pageable);

  Page<Report> getReportsByCreatedAt(LocalDateTime createdAt, Pageable pageable);

  Page<Report> getReportsByStatus(ReportStatus status, Pageable pageable);

  void deleteReportsByStatus(ReportStatus status);
}

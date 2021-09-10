package com.example.repository;

import com.example.model.report.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;

public interface ReportRepository extends PagingAndSortingRepository<Report, Long> {

  Page<Report> getReportsByReportTopicAndUser_Email(String topic, String email, Pageable pageable);

  Page<Report> getReportsByUser_Email(String email, Pageable pageable);

  Page<Report> getReportsByReportStatusAndUser_Email(
      String status, String email, Pageable pageable);

  @Override
  Page<Report> findAll(Pageable pageable);

  Page<Report> getReportsByCreatedAt(LocalDateTime createdAt, Pageable pageable);

  Page<Report> getReportsByReportStatus(String status, Pageable pageable);
}

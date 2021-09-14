package com.example.web.controller.report;

import com.example.exception.ReportNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.mapper.ReportMapper;
import com.example.mapper.dto.report.CreateReportDto;
import com.example.mapper.dto.report.DetailsReportDto;
import com.example.mapper.dto.report.ReportDto;
import com.example.mapper.dto.report.ResponseReportDto;
import com.example.model.report.Report;
import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;
import com.example.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manage/reports")
public class UserReportController {

  private final ReportMapper mapper;

  private final ReportService reportService;

  private static final String BASE_URL = "http://localhost:8080/manage/reports";

  @PostMapping("/report")
  public ResponseEntity<ResponseReportDto> createNewReport(@RequestBody CreateReportDto reportDto)
      throws UserNotFoundException {

    Report requestReport = mapper.createReportDtoToReport(reportDto);
    Report createdReport = reportService.createReport(requestReport);
    ResponseReportDto responseReportDto = mapper.reportToResponseReportDto(createdReport);

    return ResponseEntity.created(URI.create(BASE_URL)).body(responseReportDto);
  }

  @GetMapping("/report/{id}")
  public ResponseEntity<DetailsReportDto> getDetailsReportInfoById(@PathVariable("id") Long id)
      throws ReportNotFoundException {

    Report reportById = reportService.getReportById(id);
    DetailsReportDto detailsReportDto = mapper.reportToDetailsReportDto(reportById);

    return ResponseEntity.ok(detailsReportDto);
  }

  @PutMapping("/report")
  public ResponseEntity<ResponseReportDto> updateReport(
      @RequestParam("id") Long id, @RequestBody CreateReportDto updateDto)
      throws UserNotFoundException, ReportNotFoundException {

    Report report = mapper.createReportDtoToReport(updateDto);
    Report updatedReport = reportService.updateReport(id, report);
    ResponseReportDto responseReportDto = mapper.reportToResponseReportDto(updatedReport);

    return ResponseEntity.ok(responseReportDto);
  }

  @DeleteMapping("/report")
  public ResponseEntity<Void> deleteReportById(@RequestParam("id") Long id) {

    reportService.deleteReportById(id);

    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<Page<ReportDto>> getAllReports(
      @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
          Pageable pageable)
      throws UserNotFoundException {

    Page<ReportDto> allReports =
        reportService.getReportsForCurrentUser(pageable).map(mapper::reportToReportDto);

    return ResponseEntity.ok(allReports);
  }

  @GetMapping("/topic")
  public ResponseEntity<Page<ReportDto>> getReportsByTopic(
      @RequestParam("topic") ReportTopic topic,
      @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
          Pageable pageable)
      throws UserNotFoundException {

    Page<ReportDto> reportsByTopic =
        reportService
            .getReportsByTopicForCurrentUser(topic, pageable)
            .map(mapper::reportToReportDto);

    return ResponseEntity.ok(reportsByTopic);
  }

  @GetMapping("/status")
  public ResponseEntity<Page<ReportDto>> getReportsByStatus(
      @RequestParam("status") ReportStatus status,
      @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
          Pageable pageable)
      throws UserNotFoundException {

    Page<ReportDto> reportsByStatus =
        reportService
            .getReportsByStatusForCurrentUser(status, pageable)
            .map(mapper::reportToReportDto);

    return ResponseEntity.ok(reportsByStatus);
  }
}

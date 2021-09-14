package com.example.web.controller.report;

import com.example.exception.ContactNotFoundException;
import com.example.exception.ReportNotFoundException;
import com.example.mapper.ManageReportMapper;
import com.example.mapper.dto.report.manager.ManagerReportDto;
import com.example.mapper.dto.report.manager.ManagerResponseReportDto;
import com.example.mapper.dto.report.manager.UpdateReportDto;
import com.example.model.report.Report;
import com.example.service.report.ManagerReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manage/manager/reports")
public class ManagerReportController {

  private final ManageReportMapper customMapper;

  private final ManagerReportService reportService;

  @PutMapping("/report")
  public ResponseEntity<ManagerResponseReportDto> updateUsersReport(
      @RequestParam("id") Long id, @RequestBody UpdateReportDto dto)
      throws ReportNotFoundException {

    Report requestReport = customMapper.updateReportDtoToReport(id, dto);
    Report updatedReport = reportService.updateUsersReport(requestReport);
    ManagerResponseReportDto reportDto =
        customMapper.reportToManagerResponseReportDto(updatedReport);

    return ResponseEntity.ok(reportDto);
  }

  @GetMapping("/report/{id}")
  public ResponseEntity<ManagerResponseReportDto> getReportDetails(@PathVariable("id") Long id)
      throws ReportNotFoundException {

    Report usersReportById = reportService.getUsersReportById(id);
    ManagerResponseReportDto reportDto =
        customMapper.reportToManagerResponseReportDto(usersReportById);

    return ResponseEntity.ok(reportDto);
  }

  @DeleteMapping("/report")
  public ResponseEntity<Void> deleteReportById(@RequestParam("id") Long id) {

    reportService.deleteReportById(id);

    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<Page<ManagerReportDto>> getAllReports(
      @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
          Pageable pageable) {

    Page<ManagerReportDto> allReports =
        convertReportsToManagerReportDtos(reportService.getAllReports(pageable));

    return ResponseEntity.ok(allReports);
  }

  @GetMapping("/today")
  public ResponseEntity<Page<ManagerReportDto>> getTodayReports(
      @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
          Pageable pageable) {

    Page<ManagerReportDto> todayReports =
        convertReportsToManagerReportDtos(reportService.getTodayReports(pageable));

    return ResponseEntity.ok(todayReports);
  }

  @GetMapping("/{date}")
  public ResponseEntity<Page<ManagerReportDto>> getReportsByDate(
      @PathVariable("date")
          @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
          LocalDateTime date,
      @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
          Pageable pageable) {

    Page<ManagerReportDto> reportsByDate =
        convertReportsToManagerReportDtos(reportService.getReportsByCreatedAt(date, pageable));

    return ResponseEntity.ok(reportsByDate);
  }

  

  private Page<ManagerReportDto> convertReportsToManagerReportDtos(Page<Report> reports) {

    Page<ManagerReportDto> allReports = reports.map(customMapper::reportToManagerReportDto);

    return allReports;
  }
}
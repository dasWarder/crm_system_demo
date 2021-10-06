package com.example.web.controller.report;

import com.example.exception.ReportNotFoundException;
import com.example.mapper.ManageReportMapper;
import com.example.mapper.ManagerReportMapper;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/manage/manager/reports")
public class ManagerReportController {

  private final ManageReportMapper customMapper;

  private final ManagerReportMapper mapper;

  private final ManagerReportService reportService;

  @PutMapping("/report")
  public ResponseEntity<ManagerResponseReportDto> updateUsersReport(
      @RequestParam("id") @Min(value = 1, message = "The id must be greater than 0") Long id,
      @RequestBody @Valid @NotNull UpdateReportDto dto)
      throws ReportNotFoundException {

    Report requestReport = customMapper.updateReportDtoToReport(id, dto);
    Report updatedReport = reportService.updateUsersReport(requestReport);
    ManagerResponseReportDto reportDto =
        mapper.reportToManagerResponseReportDto(updatedReport);

    return ResponseEntity.ok(reportDto);
  }

  @GetMapping("/report/{id}")
  public ResponseEntity<ManagerResponseReportDto> getReportDetails(
      @PathVariable("id") @Min(value = 1, message = "The id must be greater than 0") Long id)
      throws ReportNotFoundException {

    Report usersReportById = reportService.getUsersReportById(id);
    ManagerResponseReportDto reportDto =
        mapper.reportToManagerResponseReportDto(usersReportById);

    return ResponseEntity.ok(reportDto);
  }

  @DeleteMapping("/report")
  public ResponseEntity<Void> deleteReportById(
      @RequestParam("id") @Min(value = 1, message = "The id must be greater than 0") Long id) {

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
          @NotNull(message = "The param must be not null") LocalDateTime date,
      @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
          Pageable pageable) {

    Page<ManagerReportDto> reportsByDate =
        convertReportsToManagerReportDtos(reportService.getReportsByCreatedAt(date, pageable));

    return ResponseEntity.ok(reportsByDate);
  }

  private Page<ManagerReportDto> convertReportsToManagerReportDtos(Page<Report> reports) {

    Page<ManagerReportDto> allReports = reports.map(mapper::reportToManagerReportDto);

    return allReports;
  }
}

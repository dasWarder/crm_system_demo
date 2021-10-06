package com.example.mapper;

import com.example.exception.ReportNotFoundException;
import com.example.mapper.dto.report.manager.ManagerReportDto;
import com.example.mapper.dto.report.manager.ManagerResponseReportDto;
import com.example.mapper.dto.report.manager.UpdateReportDto;
import com.example.model.contactManager.Contact;
import com.example.model.report.Report;
import com.example.service.report.ManagerReportService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class ManageReportMapper {

  @Autowired private ManagerReportService reportService;

  public ManagerResponseReportDto reportToManagerResponseReportDto(Report report) {

    Contact contactById = report.getUser().getContact();

    ManagerResponseReportDto reportDto =
        ManagerResponseReportDto.builder()
            .topic(report.getTopic())
            .authorFirstName(contactById.getFirstName())
            .authorLastName(contactById.getLastName())
            .authorEmail(contactById.getEmail())
            .comment(report.getComment())
            .status(report.getStatus())
            .response(report.getResponse())
            .createdAt(report.getCreatedAt())
            .statusChanged(report.getStatusChanged())
            .build();

    return reportDto;
  }

  public ManagerReportDto reportToManagerReportDto(Report report) {

    Contact contactById = report.getUser().getContact();

    ManagerReportDto reportDto =
        ManagerReportDto.builder()
            .id(report.getId())
            .topic(report.getTopic())
            .authorFirstName(contactById.getFirstName())
            .authorLastName(contactById.getLastName())
            .authorEmail(contactById.getEmail())
            .authorComment(report.getComment())
            .status(report.getStatus())
            .createdAt(report.getCreatedAt())
            .build();

    return reportDto;
  }

  public Report updateReportDtoToReport(Long id, UpdateReportDto dto)
      throws ReportNotFoundException {

    Report dbReport = reportService.getUsersReportById(id);
    dbReport.setStatus(dto.getStatus());
    dbReport.setResponse(dto.getResponse());

    return dbReport;
  }
}

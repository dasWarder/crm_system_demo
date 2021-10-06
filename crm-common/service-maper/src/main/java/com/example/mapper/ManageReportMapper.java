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

  public Report updateReportDtoToReport(Long id, UpdateReportDto dto)
      throws ReportNotFoundException {

    Report dbReport = reportService.getUsersReportById(id);
    dbReport.setStatus(dto.getStatus());
    dbReport.setResponse(dto.getResponse());

    return dbReport;
  }
}

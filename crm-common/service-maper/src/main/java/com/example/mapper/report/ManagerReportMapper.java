package com.example.mapper.report;

import com.example.mapper.user.UserMapper;
import com.example.mapper.contact.ContactMapper;
import com.example.mapper.dto.report.manager.ManagerReportDto;
import com.example.mapper.dto.report.manager.ManagerResponseReportDto;
import com.example.mapper.dto.report.manager.UpdateReportDto;
import com.example.model.report.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ContactMapper.class, UserMapper.class})
public interface ManagerReportMapper {

  @Mapping(target = "authorEmail", source = "report.user.contact.email")
  @Mapping(target = "authorFirstName", source = "report.user.contact.firstName")
  @Mapping(target = "authorLastName", source = "report.user.contact.lastName")
  ManagerResponseReportDto reportToManagerResponseReportDto(Report report);

  @Mapping(target = "authorEmail", source = "report.user.contact.email")
  @Mapping(target = "authorFirstName", source = "report.user.contact.firstName")
  @Mapping(target = "authorLastName", source = "report.user.contact.lastName")
  ManagerReportDto reportToManagerReportDto(Report report);

  @Mapping(target = "status", source = "dto.status")
  @Mapping(target = "response", source = "dto.response")
  Report updateReportDtoToReport(Report reportById, UpdateReportDto dto);
}

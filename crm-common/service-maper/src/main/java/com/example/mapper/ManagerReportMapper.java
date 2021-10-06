package com.example.mapper;

import com.example.mapper.dto.report.manager.ManagerResponseReportDto;
import com.example.model.report.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ContactMapper.class, UserMapper.class})
public interface ManagerReportMapper {

  @Mapping(target = "authorEmail", source = "report.user.contact.email")
  @Mapping(target = "authorFirstName", source = "report.user.contact.firstName")
  @Mapping(target = "authorLastName", source = "report.user.contact.lastName")
  ManagerResponseReportDto reportToManagerResponseReportDto(Report report);
}

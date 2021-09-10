package com.example.mapper;

import com.example.mapper.dto.report.CreatedReportDto;
import com.example.mapper.dto.report.ReportDto;
import com.example.model.report.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReportMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "response", ignore = true)
    @Mapping(target = "topic", source = "topic")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "status", source = "status")
    @Mapping(target = "comment", source = "comment")
    @Mapping(target = "statusChanged", ignore = true)
    Report reportDtoToReport(ReportDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "topic", source = "topic")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "comment", source = "comment")
    @Mapping(target = "createAt", source = "createAt")
    @Mapping(target = "statusChanged", source = "statusChanged")
    CreatedReportDto reportToCreatedReportDto(Report report);
}

package com.example.mapper;

import com.example.mapper.dto.report.CreateReportDto;
import com.example.mapper.dto.report.ResponseReportDto;
import com.example.mapper.dto.report.DetailsReportDto;
import com.example.mapper.dto.report.ReportDto;
import com.example.mapper.dto.report.manager.UpdateReportDto;
import com.example.model.report.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReportMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "response", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "statusChanged", ignore = true)
    Report createReportDtoToReport(CreateReportDto dto);

    @Mapping(target = "response", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "statusChanged", ignore = true)
    Report reportDtoToReport(ReportDto dto);

    ReportDto reportToReportDto(Report report);

    ResponseReportDto reportToResponseReportDto(Report report);

    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "statusChanged", source = "statusChanged")
    DetailsReportDto reportToDetailsReportDto(Report report);

    @Mapping(target = "topic", source = "topic")
    @Mapping(target = "comment", source = "comment")
    CreateReportDto reportToCreateReportDto(Report report);

    UpdateReportDto reportToUpdateReportDto(Report report);
}

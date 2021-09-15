package com.example.mapper;

import com.example.mapper.dto.dashboard.DashboardDto;
import com.example.model.report.Report;
import com.example.model.todoList.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = { ReportMapper.class, TaskMapper.class })
public interface DashboardMapper {

  @Mapping(target = "daysInCompany", source = "daysInCompany")
  @Mapping(target = "reports", source = "reports")
  @Mapping(target = "lastReports", source = "lastReports")
  @Mapping(target = "activeTasks",  source = "activeTasks")
  @Mapping(target = "lastActiveTasks", source = "lastActiveTasks")
  DashboardDto toDashboardDto(
      Long daysInCompany,
      Long reports,
      List<Report> lastReports,
      Long activeTasks,
      List<Task> lastActiveTasks);
}

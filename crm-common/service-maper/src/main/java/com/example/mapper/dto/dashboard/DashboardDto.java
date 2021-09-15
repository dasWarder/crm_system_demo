package com.example.mapper.dto.dashboard;

import com.example.mapper.dto.report.ReportDto;
import com.example.mapper.dto.task.TaskDto;
import com.example.model.report.Report;
import com.example.model.todoList.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDto {

  private Long daysInCompany;

  private Long reports;

  private List<ReportDto> lastReports;

  private Long activeTasks;

  private List<TaskDto> lastActiveTasks;
}

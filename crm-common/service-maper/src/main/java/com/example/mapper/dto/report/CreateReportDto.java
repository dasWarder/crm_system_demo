package com.example.mapper.dto.report;

import com.example.model.report.ReportTopic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReportDto {

  private ReportTopic topic;

  private String comment;
}

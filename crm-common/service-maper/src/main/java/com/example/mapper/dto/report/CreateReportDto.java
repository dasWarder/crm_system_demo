package com.example.mapper.dto.report;

import com.example.model.report.ReportTopic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReportDto {

  @NotNull(message = "The field is mandatory")
  private ReportTopic topic;

  private String comment;
}

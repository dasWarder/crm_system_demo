package com.example.mapper.dto.report;

import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {"id", "topic", "comment", "status", "createdAt", "statusChanged"})
public class ResponseReportDto extends BaseDateDto {

  private Long id;

  @NotBlank(message = "The field is mandatory")
  private ReportTopic topic;

  private String comment;

  @NotBlank(message = "The field is mandatory")
  private ReportStatus status;
}

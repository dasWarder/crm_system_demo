package com.example.mapper.dto.report;

import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(
    value = {"id", "topic", "comment", "status", "createdAt", "statusChanged", "response"})
public class DetailsReportDto extends BaseDateDto {

  private Long id;

  private ReportTopic topic;

  private String comment;

  private ReportStatus status;

  private String response;
}

package com.example.mapper.dto.report;

import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {"id", "topic", "comment", "status"})
public class ReportDto {

  private Long id;

  private ReportTopic topic;

  private String comment;

  private ReportStatus status;
}

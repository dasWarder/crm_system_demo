package com.example.mapper.dto.report;

import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(
    value = {"id", "topic", "comment", "status", "createdAt", "statusChanged", "response"})
public class DetailsReportDto extends BaseDateDto {

  public DetailsReportDto(LocalDateTime createdAt, LocalDateTime statusChanged) {
    super(createdAt, statusChanged);
  }

  public DetailsReportDto(
      LocalDateTime createdAt,
      LocalDateTime statusChanged,
      Long id,
      ReportTopic topic,
      String comment,
      ReportStatus status,
      String response) {
    super(createdAt, statusChanged);
    this.id = id;
    this.topic = topic;
    this.comment = comment;
    this.status = status;
    this.response = response;
  }

  private Long id;

  @NotBlank(message = "The field is mandatory")
  private ReportTopic topic;

  private String comment;

  @NotBlank(message = "The field is mandatory")
  private ReportStatus status;

  private String response;
}

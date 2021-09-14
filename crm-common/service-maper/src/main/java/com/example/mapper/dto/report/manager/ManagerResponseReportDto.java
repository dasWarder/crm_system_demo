package com.example.mapper.dto.report.manager;

import com.example.mapper.dto.report.BaseDateDto;
import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerResponseReportDto extends BaseDateDto {

  public ManagerResponseReportDto(LocalDateTime startedAt, LocalDateTime statusChanged) {
    super(startedAt, statusChanged);
  }

  private ReportTopic topic;

  private String authorFirstName;

  private String authorLastName;

  private String authorEmail;

  private String comment;

  private ReportStatus status;

  private String response;
}

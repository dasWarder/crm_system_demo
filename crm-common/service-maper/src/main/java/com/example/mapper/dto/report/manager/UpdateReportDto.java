package com.example.mapper.dto.report.manager;

import com.example.model.report.ReportStatus;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReportDto {

  @NotNull(message = "The field is mandatory")
  private ReportStatus status;

  private String response;
}

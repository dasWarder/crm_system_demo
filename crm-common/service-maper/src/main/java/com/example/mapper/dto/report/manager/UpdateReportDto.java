package com.example.mapper.dto.report.manager;

import com.example.model.report.ReportStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReportDto {

  private ReportStatus status;

  private String response;
}

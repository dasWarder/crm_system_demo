package com.example.mapper.dto.report.manager;

import com.example.mapper.dto.report.BaseDateDto;
import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

  @NotBlank(message = "The field is mandatory")
  private ReportTopic topic;

  @NotBlank(message = "The field is mandatory")
  private String authorFirstName;

  @NotBlank(message = "The field is mandatory")
  private String authorLastName;

  @NotBlank(message = "The field is mandatory")
  @Email(message = "The field must be a valid email")
  private String authorEmail;

  private String comment;

  @NotNull(message = "The field is mandatory")
  private ReportStatus status;

  private String response;
}

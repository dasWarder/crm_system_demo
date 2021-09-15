package com.example.mapper.dto.report.manager;

import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
public class ManagerReportDto {

  private Long id;

  @NotBlank(message = "The field is mandatory")
  private ReportTopic topic;

  @NotNull(message = "The field is mandatory")
  private String authorFirstName;

  @NotBlank(message = "The field is mandatory")
  private String authorLastName;

  @NotBlank(message = "The field is mandatory")
  @Email(message = "The field must be a valid email")
  private String authorEmail;

  private String authorComment;

  @NotNull
  private ReportStatus status;

  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime createdAt;
}

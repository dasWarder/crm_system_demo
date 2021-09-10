package com.example.mapper.dto.report;

import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatedReportDto {

    private Long id;

    private ReportTopic topic;

    private String comment;

    private ReportStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime statusChangedAt;
}

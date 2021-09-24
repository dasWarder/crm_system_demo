package com.example.web.data;

import com.example.model.report.Report;
import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public class TestReportData {

  public static final Report TEST_REPORT_1 =
      Report.builder()
          .id(1L)
          .topic(ReportTopic.VACATION)
          .status(ReportStatus.RECEIVED)
          .createdAt(LocalDateTime.of(2021, 9, 05, 13, 44, 00))
          .statusChanged(LocalDateTime.of(2021, 9, 05, 13, 44, 00))
          .build();

  public static final Report TEST_REPORT_2 =
      Report.builder()
          .id(2L)
          .topic(ReportTopic.LEAVE)
          .status(ReportStatus.RECEIVED)
          .createdAt(LocalDateTime.of(2021, 9, 06, 17, 01, 00))
          .statusChanged(LocalDateTime.of(2021, 9, 06, 17, 01, 00))
          .build();

  public static final Report TEST_REPORT_3 =
      Report.builder()
          .id(3L)
          .topic(ReportTopic.UNPAID)
          .comment("Have 1 day leave due to domestic troubles")
          .status(ReportStatus.APPROVED)
          .createdAt(LocalDateTime.of(2021, 9, 7, 12, 00, 00))
          .statusChanged(LocalDateTime.of(2021, 9, 8, 13, 47, 00))
          .build();

  public static final Report TEST_SAVE_REPORT =
      Report.builder()
          .id(4L)
          .topic(ReportTopic.ILLNESS)
          .comment("Illness leave from 12.09 till 19.09")
          .status(ReportStatus.DENIED)
          .createdAt(LocalDateTime.of(2021, 9, 11, 8, 32, 00))
          .statusChanged(LocalDateTime.of(2021, 9, 11, 11, 30, 00))
          .comment("You have only 5 possible days for illness leave")
          .build();

  public static final Report TEST_UPDATE_REPORT =
      Report.builder()
          .id(TEST_REPORT_2.getId())
          .topic(ReportTopic.UNPAID)
          .comment("Leave from 12.09 till 15.09")
          .status(ReportStatus.RECEIVED)
          .createdAt(LocalDateTime.of(2021, 9, 06, 17, 01, 00))
          .statusChanged(LocalDateTime.of(2021, 9, 06, 17, 01, 00))
          .build();
}

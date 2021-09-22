package com.example.service.report;

import com.example.model.report.Report;
import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;

import java.time.LocalDateTime;

public class ReportTestData {

  public static final Report TEST_REPORT_1 =
      new Report(
          1L,
          ReportTopic.VACATION,
          "Vacation from 21.09 till 30.09",
          ReportStatus.RECEIVED,
          LocalDateTime.now(),
          LocalDateTime.now(),
          null,
          null);

  public static final Report TEST_REPORT_2 =
      new Report(
          2L,
          ReportTopic.LEAVE,
          "Leave from a job from 30.10",
          ReportStatus.APPROVED,
          LocalDateTime.of(1990, 10, 10, 12, 30, 00),
          LocalDateTime.of(1990, 10, 12, 15, 32, 00),
          null,
          null);

  public static final Report TEST_REPORT_3 =
      new Report(
          3L,
          ReportTopic.UNPAID,
          "Unpaid vacation from 12.01 till 17.01",
          ReportStatus.RETURNED,
          LocalDateTime.of(2020, 12, 22, 12, 30, 00),
          LocalDateTime.of(2021, 01, 15, 12, 00, 00),
          null,
          "Must be permitted by a manager");

  public static final Report TEST_REPORT_4 =
      new Report(
          4L,
          ReportTopic.ILLNESS,
          "Illness vacation from 13.04 till 20.04",
          ReportStatus.RECEIVED,
          LocalDateTime.now(),
          LocalDateTime.now(),
          null,
          null);

  public static final Report TEST_STORE_REPORT =
      new Report(
          5L,
          ReportTopic.VACATION,
          "Vacation from 12.02 till 22.02",
          null,
          LocalDateTime.now(),
          LocalDateTime.now(),
          null,
          null);

  public static final Report TEST_UPDATE_REPORT =
      new Report(
          TEST_REPORT_3.getId(),
          ReportTopic.UNPAID,
          "Unpaid vacation from 15.01 till 17.01",
          ReportStatus.APPROVED,
          LocalDateTime.of(2020, 12, 22, 12, 30, 00),
          LocalDateTime.now(),
          null,
          "The unpaid vacation approved");
}

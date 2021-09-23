package com.example.service.report;

import com.example.exception.ReportNotFoundException;
import com.example.model.report.Report;
import com.example.model.report.ReportStatus;
import com.example.repository.ReportRepository;
import com.example.service.util.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.service.contact.ContactTestData.TEST_PAGEABLE;
import static com.example.service.report.ReportTestData.*;
import static com.example.service.util.TestUtil.getListOfObjects;

@Slf4j
class ManagerReportServiceImplTest {

  private final ReportRepository reportRepository = Mockito.mock(ReportRepository.class);

  private final ManagerReportService reportService = new ManagerReportServiceImpl(reportRepository);

  @Test
  public void shouldUpdateUsersReportProperly() {

    log.info("Test updateUsersReport() method");

    Mockito.when(reportRepository.save(TEST_UPDATE_REPORT)).thenReturn(TEST_UPDATE_REPORT);

    Report updatedReport = reportService.updateUsersReport(TEST_UPDATE_REPORT);

    Assertions.assertNotNull(updatedReport);
    Assertions.assertEquals(TEST_UPDATE_REPORT, updatedReport);
  }

  @Test
  public void shouldGetUsersReportByIdProperly() throws ReportNotFoundException {

    log.info("Test getUsersReportById() method");

    Mockito.when(reportRepository.findById(TEST_REPORT_1.getId()))
        .thenReturn(Optional.of(TEST_REPORT_1));

    Report reportById = reportService.getUsersReportById(TEST_REPORT_1.getId());

    Assertions.assertNotNull(reportById);
    Assertions.assertEquals(TEST_REPORT_1, reportById);
  }

  @Test
  public void shouldThrowExceptionWhenGetUsersReportByIdWithNullableId() {

    log.info("Test getUsersReportById() method throws an exception when id is nullable");

    Assertions.assertThrows(
        ReportNotFoundException.class, () -> reportService.getUsersReportById(null));
  }

  @Test
  public void shouldDeleteUserReportByIdProperly() {

    log.info("Test deleteUserReportById() method");
    reportService.deleteReportById(TEST_REPORT_1.getId());
  }

  @Test
  public void shouldDeleteUserReportByStatusProperly() {

    log.info("Test deleteUserReportsByStatus() method");
    reportService.deleteReportsByStatus(ReportStatus.APPROVED);
  }

  @Test
  public void shouldGetAllReportsProperly() {

    log.info("Test getAllReports() method");

    List<Report> reports =
        getListOfObjects(TEST_REPORT_1, TEST_REPORT_2, TEST_REPORT_3, TEST_REPORT_4);
    PageImpl<Report> reportsPage = new PageImpl<>(reports);

    Mockito.when(reportRepository.findAll(TEST_PAGEABLE)).thenReturn(reportsPage);
    Page<Report> actualReportsPage = reportService.getAllReports(TEST_PAGEABLE);

    Assertions.assertNotNull(actualReportsPage);
    Assertions.assertEquals(reports, actualReportsPage.getContent());
  }

  @Test
  public void shouldGetTodayReportsProperly() {

    log.info("Test getTodayReports() method");

    List<Report> reports = getListOfObjects(TEST_REPORT_1, TEST_REPORT_4);
    PageImpl<Report> reportsPage = new PageImpl<>(reports);
    LocalDateTime currentDateTime = LocalDateTime.now();
    LocalDateTime today =
        LocalDateTime.of(
            currentDateTime.getYear(),
            currentDateTime.getMonth(),
            currentDateTime.getDayOfMonth(),
            00,
            00,
            00);

    Mockito.when(reportRepository.getReportsByCreatedAtAfter(today, TEST_PAGEABLE))
        .thenReturn(reportsPage);
    Page<Report> actualPage = reportService.getTodayReports(TEST_PAGEABLE);

    Assertions.assertNotNull(actualPage);
    Assertions.assertEquals(reports, actualPage.getContent());
  }

  @Test
  public void shouldGetReportsByCreatedAtProperly() {

    log.info("Test getReportsByCreatedAt() method");

    List<Report> reports = getListOfObjects(TEST_REPORT_1, TEST_REPORT_4);
    PageImpl<Report> reportsPage = new PageImpl<>(reports);

    Mockito.when(
            reportRepository.getReportsByCreatedAtAfter(
                TEST_REPORT_1.getCreatedAt(), TEST_PAGEABLE))
        .thenReturn(reportsPage);
    Page<Report> actualPage =
        reportService.getReportsByCreatedAt(TEST_REPORT_1.getCreatedAt(), TEST_PAGEABLE);

    Assertions.assertNotNull(actualPage);
    Assertions.assertEquals(reports, actualPage.getContent());
  }

  @Test
  public void shouldGetAlreadyFinishedReportsProperly() {

    log.info("Test getAlreadyFinishedReports() method");

    List<Report> approvedReports = getListOfObjects(TEST_REPORT_2);
    PageImpl<Report> reportsPage = new PageImpl<>(approvedReports);

    Mockito.when(reportRepository.getReportsByStatus(ReportStatus.APPROVED, TEST_PAGEABLE))
        .thenReturn(reportsPage);
    Mockito.when(reportRepository.getReportsByStatus(ReportStatus.DENIED, TEST_PAGEABLE))
        .thenReturn(new PageImpl<>(new ArrayList<>()));

    Page<Report> actualPage = reportService.getAlreadyFinishedReports(TEST_PAGEABLE);

    Assertions.assertNotNull(actualPage);
    Assertions.assertEquals(approvedReports, actualPage.getContent());
  }
}

package com.example.service.report;

import com.example.exception.ReportNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.model.report.Report;
import com.example.model.report.ReportStatus;
import com.example.model.report.ReportTopic;
import com.example.repository.ReportRepository;
import com.example.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.example.service.report.ReportTestData.*;
import static com.example.service.user.UserTestData.TEST_USER_1;
import static com.example.service.util.TestUtil.getListOfObjects;

@Slf4j
class UserReportServiceImplTest {

  private final UserService userService = Mockito.mock(UserService.class);

  private static final Pageable TEST_PAGEABLE = PageRequest.of(0, 20);

  private final ReportRepository reportRepository = Mockito.mock(ReportRepository.class);

  private final UserReportService reportService =
      new UserReportServiceImpl(userService, reportRepository);

  @Test
  public void shouldCreateReportProperly() throws UserNotFoundException {

    log.info("Test createReport() method");

    Mockito.when(userService.getCurrentUser()).thenReturn(TEST_USER_1);
    Mockito.when(reportRepository.save(TEST_STORE_REPORT)).thenReturn(TEST_STORE_REPORT);

    Report report = reportService.createReport(TEST_STORE_REPORT);

    Assertions.assertNotNull(report);
    Assertions.assertEquals(TEST_STORE_REPORT, report);
  }

  @Test
  public void shouldUpdateReportProperly() throws UserNotFoundException, ReportNotFoundException {

    log.info("Test updateReport() method");

    Mockito.when(reportRepository.findById(TEST_REPORT_3.getId()))
        .thenReturn(Optional.of(TEST_REPORT_3));
    Mockito.when(reportRepository.save(TEST_UPDATE_REPORT)).thenReturn(TEST_UPDATE_REPORT);

    Report report = reportService.updateReport(TEST_REPORT_3.getId(), TEST_UPDATE_REPORT);

    Assertions.assertNotNull(report);
    Assertions.assertEquals(TEST_UPDATE_REPORT, report);
  }

  @Test
  public void shouldThrowExceptionWhenUpdateReportUpdateWithNullableId() {

    log.info("Test updateReport() method throws exception when the report id is null");
    Assertions.assertThrows(
        ReportNotFoundException.class, () -> reportService.updateReport(null, TEST_UPDATE_REPORT));
  }

  @Test
  public void shouldGetReportByIdProperly() throws ReportNotFoundException {

    log.info("Test getReportById() method");
    Mockito.when(reportRepository.findById(TEST_REPORT_1.getId()))
        .thenReturn(Optional.of(TEST_REPORT_1));

    Report reportById = reportService.getReportById(TEST_REPORT_1.getId());

    Assertions.assertNotNull(reportById);
    Assertions.assertEquals(TEST_REPORT_1, reportById);
  }

  @Test
  public void shouldThrowExceptionWhenGetMethodByIdWithNullableId() {

    log.info("Test getReportById() method throws an exception when id is nullable");

    Assertions.assertThrows(ReportNotFoundException.class, () -> reportService.getReportById(null));
  }

  @Test
  public void shouldDeleteReportByIdProperly() {

    log.info("Test deleteReportById() method");
    reportService.deleteReportById(TEST_REPORT_1.getId());
  }

  @Test
  public void shouldGetReportsForCurrentUserProperly() throws UserNotFoundException {

    log.info("Test getReportsForCurrentUser() method");

    List<Report> reports = getListOfObjects(TEST_REPORT_1, TEST_REPORT_2);
    PageImpl<Report> allReports = new PageImpl<>(reports);

    Mockito.when(userService.getCurrentUser()).thenReturn(TEST_USER_1);
    Mockito.when(reportRepository.getReportsByUser_Email(TEST_USER_1.getEmail(), TEST_PAGEABLE))
        .thenReturn(allReports);

    Page<Report> actualReports = reportService.getReportsForCurrentUser(TEST_PAGEABLE);

    Assertions.assertNotNull(actualReports);
    Assertions.assertEquals(reports, actualReports.getContent());
  }

  @Test
  public void shouldGetReportsByTopicForCurrentUserProperly() throws UserNotFoundException {

    log.info("Test getReportsByTopicForCurrentUser() method");

    List<Report> vacationReports = getListOfObjects(TEST_REPORT_1);
    PageImpl<Report> reports = new PageImpl<>(vacationReports);

    Mockito.when(userService.getCurrentUser()).thenReturn(TEST_USER_1);
    Mockito.when(
            reportRepository.getReportsByTopicAndUser_Email(
                ReportTopic.VACATION, TEST_USER_1.getEmail(), TEST_PAGEABLE))
        .thenReturn(reports);

    Page<Report> actualReports =
        reportService.getReportsByTopicForCurrentUser(ReportTopic.VACATION, TEST_PAGEABLE);

    Assertions.assertNotNull(actualReports);
    Assertions.assertEquals(vacationReports, actualReports.getContent());
  }

  @Test
  public void shouldGetReportsByStatusForCurrentUserProperly() throws UserNotFoundException {

    log.info("Test getReportsByStatusForCurrentUser() method");

    List<Report> reports = getListOfObjects(TEST_REPORT_1, TEST_REPORT_4);
    PageImpl<Report> reportsPage = new PageImpl<>(reports);

    Mockito.when(userService.getCurrentUser()).thenReturn(TEST_USER_1);
    Mockito.when(
            reportRepository.getReportsByStatusAndUser_Email(
                ReportStatus.RECEIVED, TEST_USER_1.getEmail(), TEST_PAGEABLE))
        .thenReturn(reportsPage);

    Page<Report> actualReports =
        reportService.getReportsByStatusForCurrentUser(ReportStatus.RECEIVED, TEST_PAGEABLE);

    Assertions.assertNotNull(actualReports);
    Assertions.assertEquals(reports, actualReports.getContent());
  }

  @Test
  public void shouldGetLastCurrentUserReportsProperly() throws UserNotFoundException {

    log.info("Test getLastCurrentUserReport() method");

    List<Report> reportsList =
        getListOfObjects(TEST_REPORT_1, TEST_REPORT_2, TEST_REPORT_3, TEST_REPORT_4);
    PageImpl<Report> reports = new PageImpl<>(reportsList);

    Mockito.when(userService.getCurrentUser()).thenReturn(TEST_USER_1);
    Mockito.when(reportService.getReportsForCurrentUser(TEST_PAGEABLE)).thenReturn(reports);

    List<Report> lastCurrentUserReports = reportService.getLastCurrentUserReports(TEST_PAGEABLE);

    Assertions.assertNotNull(lastCurrentUserReports);
  }
}

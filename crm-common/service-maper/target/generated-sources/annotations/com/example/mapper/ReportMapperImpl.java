package com.example.mapper;

import com.example.mapper.dto.report.CreateReportDto;
import com.example.mapper.dto.report.DetailsReportDto;
import com.example.mapper.dto.report.ReportDto;
import com.example.mapper.dto.report.ResponseReportDto;
import com.example.model.report.Report;
import com.example.model.report.Report.ReportBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-14T14:01:56+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class ReportMapperImpl implements ReportMapper {

    @Override
    public Report createReportDtoToReport(CreateReportDto dto) {
        if ( dto == null ) {
            return null;
        }

        ReportBuilder report = Report.builder();

        report.topic( dto.getTopic() );
        report.comment( dto.getComment() );

        return report.build();
    }

    @Override
    public Report reportDtoToReport(ReportDto dto) {
        if ( dto == null ) {
            return null;
        }

        ReportBuilder report = Report.builder();

        report.id( dto.getId() );
        report.topic( dto.getTopic() );
        report.comment( dto.getComment() );
        report.status( dto.getStatus() );

        return report.build();
    }

    @Override
    public ReportDto reportToReportDto(Report report) {
        if ( report == null ) {
            return null;
        }

        ReportDto reportDto = new ReportDto();

        reportDto.setId( report.getId() );
        reportDto.setTopic( report.getTopic() );
        reportDto.setComment( report.getComment() );
        reportDto.setStatus( report.getStatus() );

        return reportDto;
    }

    @Override
    public ResponseReportDto reportToResponseReportDto(Report report) {
        if ( report == null ) {
            return null;
        }

        ResponseReportDto responseReportDto = new ResponseReportDto();

        responseReportDto.setCreatedAt( report.getCreatedAt() );
        responseReportDto.setStatusChanged( report.getStatusChanged() );
        responseReportDto.setId( report.getId() );
        responseReportDto.setTopic( report.getTopic() );
        responseReportDto.setComment( report.getComment() );
        responseReportDto.setStatus( report.getStatus() );

        return responseReportDto;
    }

    @Override
    public DetailsReportDto reportToDetailsReportDto(Report report) {
        if ( report == null ) {
            return null;
        }

        DetailsReportDto detailsReportDto = new DetailsReportDto();

        detailsReportDto.setCreatedAt( report.getCreatedAt() );
        detailsReportDto.setStatusChanged( report.getStatusChanged() );
        detailsReportDto.setId( report.getId() );
        detailsReportDto.setTopic( report.getTopic() );
        detailsReportDto.setComment( report.getComment() );
        detailsReportDto.setStatus( report.getStatus() );
        detailsReportDto.setResponse( report.getResponse() );

        return detailsReportDto;
    }
}
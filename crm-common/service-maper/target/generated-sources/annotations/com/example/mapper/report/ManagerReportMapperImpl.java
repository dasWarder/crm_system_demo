package com.example.mapper.report;

import com.example.mapper.dto.report.manager.ManagerReportDto;
import com.example.mapper.dto.report.manager.ManagerResponseReportDto;
import com.example.mapper.dto.report.manager.UpdateReportDto;
import com.example.model.contactManager.Contact;
import com.example.model.report.Report;
import com.example.model.report.Report.ReportBuilder;
import com.example.model.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-06T15:13:14+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class ManagerReportMapperImpl implements ManagerReportMapper {

    @Override
    public ManagerResponseReportDto reportToManagerResponseReportDto(Report report) {
        if ( report == null ) {
            return null;
        }

        ManagerResponseReportDto managerResponseReportDto = new ManagerResponseReportDto();

        managerResponseReportDto.setAuthorEmail( reportUserContactEmail( report ) );
        managerResponseReportDto.setAuthorFirstName( reportUserContactFirstName( report ) );
        managerResponseReportDto.setAuthorLastName( reportUserContactLastName( report ) );
        managerResponseReportDto.setCreatedAt( report.getCreatedAt() );
        managerResponseReportDto.setStatusChanged( report.getStatusChanged() );
        managerResponseReportDto.setTopic( report.getTopic() );
        managerResponseReportDto.setComment( report.getComment() );
        managerResponseReportDto.setStatus( report.getStatus() );
        managerResponseReportDto.setResponse( report.getResponse() );

        return managerResponseReportDto;
    }

    @Override
    public ManagerReportDto reportToManagerReportDto(Report report) {
        if ( report == null ) {
            return null;
        }

        ManagerReportDto managerReportDto = new ManagerReportDto();

        managerReportDto.setAuthorEmail( reportUserContactEmail( report ) );
        managerReportDto.setAuthorFirstName( reportUserContactFirstName( report ) );
        managerReportDto.setAuthorLastName( reportUserContactLastName( report ) );
        managerReportDto.setId( report.getId() );
        managerReportDto.setTopic( report.getTopic() );
        managerReportDto.setStatus( report.getStatus() );
        managerReportDto.setCreatedAt( report.getCreatedAt() );

        return managerReportDto;
    }

    @Override
    public Report updateReportDtoToReport(Report reportById, UpdateReportDto dto) {
        if ( reportById == null && dto == null ) {
            return null;
        }

        ReportBuilder report = Report.builder();

        if ( reportById != null ) {
            report.id( reportById.getId() );
            report.topic( reportById.getTopic() );
            report.comment( reportById.getComment() );
            report.createdAt( reportById.getCreatedAt() );
            report.statusChanged( reportById.getStatusChanged() );
            report.user( reportById.getUser() );
        }
        if ( dto != null ) {
            report.status( dto.getStatus() );
            report.response( dto.getResponse() );
        }

        return report.build();
    }

    private String reportUserContactEmail(Report report) {
        if ( report == null ) {
            return null;
        }
        User user = report.getUser();
        if ( user == null ) {
            return null;
        }
        Contact contact = user.getContact();
        if ( contact == null ) {
            return null;
        }
        String email = contact.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private String reportUserContactFirstName(Report report) {
        if ( report == null ) {
            return null;
        }
        User user = report.getUser();
        if ( user == null ) {
            return null;
        }
        Contact contact = user.getContact();
        if ( contact == null ) {
            return null;
        }
        String firstName = contact.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String reportUserContactLastName(Report report) {
        if ( report == null ) {
            return null;
        }
        User user = report.getUser();
        if ( user == null ) {
            return null;
        }
        Contact contact = user.getContact();
        if ( contact == null ) {
            return null;
        }
        String lastName = contact.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }
}

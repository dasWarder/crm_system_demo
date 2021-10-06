package com.example.mapper;

import com.example.mapper.dto.dashboard.DashboardDto;
import com.example.mapper.dto.report.ReportDto;
import com.example.mapper.dto.task.TaskDto;
import com.example.model.report.Report;
import com.example.model.todoList.Task;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-06T14:36:22+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (AdoptOpenJDK)"
)
@Component
public class DashboardMapperImpl implements DashboardMapper {

    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public DashboardDto toDashboardDto(Long daysInCompany, Long reports, List<Report> lastReports, Long activeTasks, List<Task> lastActiveTasks) {
        if ( daysInCompany == null && reports == null && lastReports == null && activeTasks == null && lastActiveTasks == null ) {
            return null;
        }

        DashboardDto dashboardDto = new DashboardDto();

        if ( daysInCompany != null ) {
            dashboardDto.setDaysInCompany( daysInCompany );
        }
        if ( reports != null ) {
            dashboardDto.setReports( reports );
        }
        if ( lastReports != null ) {
            dashboardDto.setLastReports( reportListToReportDtoList( lastReports ) );
        }
        if ( activeTasks != null ) {
            dashboardDto.setActiveTasks( activeTasks );
        }
        if ( lastActiveTasks != null ) {
            dashboardDto.setLastActiveTasks( taskListToTaskDtoList( lastActiveTasks ) );
        }

        return dashboardDto;
    }

    protected List<ReportDto> reportListToReportDtoList(List<Report> list) {
        if ( list == null ) {
            return null;
        }

        List<ReportDto> list1 = new ArrayList<ReportDto>( list.size() );
        for ( Report report : list ) {
            list1.add( reportMapper.reportToReportDto( report ) );
        }

        return list1;
    }

    protected List<TaskDto> taskListToTaskDtoList(List<Task> list) {
        if ( list == null ) {
            return null;
        }

        List<TaskDto> list1 = new ArrayList<TaskDto>( list.size() );
        for ( Task task : list ) {
            list1.add( taskMapper.taskToTaskDto( task ) );
        }

        return list1;
    }
}

package com.example.mapper.task;

import com.example.mapper.dto.task.TaskDto;
import com.example.model.todoList.Task;
import com.example.model.todoList.Task.TaskBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-08T17:20:23+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDto taskToTaskDto(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDto taskDto = new TaskDto();

        taskDto.setId( task.getId() );
        taskDto.setTitle( task.getTitle() );
        taskDto.setDescription( task.getDescription() );
        taskDto.setStartFrom( task.getStartFrom() );
        taskDto.setDeadline( task.getDeadline() );

        return taskDto;
    }

    @Override
    public Task taskDtoToTask(TaskDto dto) {
        if ( dto == null ) {
            return null;
        }

        TaskBuilder task = Task.builder();

        task.id( dto.getId() );
        task.title( dto.getTitle() );
        task.description( dto.getDescription() );
        task.startFrom( dto.getStartFrom() );
        task.deadline( dto.getDeadline() );

        return task.build();
    }
}

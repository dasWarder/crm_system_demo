package com.example.mapper;

import com.example.mapper.dto.task.TaskDto;
import com.example.model.todoList.Task;
import org.mapstruct.Mapper;

@Mapper
public interface TaskMapper {

    TaskDto taskToTaskDto(Task task);
}

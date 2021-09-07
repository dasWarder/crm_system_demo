package com.example;

import com.example.dto.task.TaskDto;
import com.example.todoList.Task;
import org.mapstruct.Mapper;

@Mapper
public interface TaskMapper {

    TaskDto taskToTaskDto(Task task);
}

package com.example.mapper;

import com.example.mapper.dto.task.TaskDto;
import com.example.model.todoList.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TaskMapper {

  TaskDto taskToTaskDto(Task task);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "title", source = "title")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "startFrom", source = "startFrom")
  @Mapping(target = "deadline", source = "deadline")
  @Mapping(target = "todoList", ignore = true)
  Task taskDtoToTask(TaskDto dto);
}

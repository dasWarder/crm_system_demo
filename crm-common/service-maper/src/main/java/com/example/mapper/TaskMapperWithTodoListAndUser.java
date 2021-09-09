package com.example.mapper;

import com.example.mapper.dto.task.TaskDto;
import com.example.exception.TodoListNotFoundException;
import com.example.service.todoList.TodoListService;
import com.example.model.todoList.Task;
import com.example.model.todoList.TodoList;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class TaskMapperWithTodoListAndUser {

    @Autowired
    private TodoListService todoListService;

    public Task taskDtoToTask(TaskDto taskDto) throws TodoListNotFoundException {

        TodoList todoList = todoListService.getTodoListById(1L);

        Task task = Task.builder()
                        .id(taskDto.getId())
                        .title(taskDto.getTitle())
                        .description(taskDto.getDescription())
                        .startFrom(taskDto.getStartFrom())
                        .deadline(taskDto.getDeadline())
                        .todoList(todoList)
                        .build();

        return task;
    }

}

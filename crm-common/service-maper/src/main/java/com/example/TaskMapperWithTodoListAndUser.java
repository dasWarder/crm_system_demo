package com.example;

import com.example.dto.task.TaskDto;
import com.example.exception.TodoListNotFoundException;
import com.example.repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper
@Component
public abstract class TaskMapperWithTodoListAndUser {

    @Autowired
    private TodoListRepository todoListRepository;

    public Task taskDtoToTask(TaskDto taskDto) throws TodoListNotFoundException {

        TodoList todoList = todoListRepository.findById(1L)
                                                .orElseThrow(TodoListNotFoundException::new);

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

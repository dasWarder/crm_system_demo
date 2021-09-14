package com.example.repository;

import com.example.model.todoList.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface TodoListRepository extends JpaRepository<TodoList, Long> {}

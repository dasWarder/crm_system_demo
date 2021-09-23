package com.example.web.data;

import com.example.model.todoList.TodoList;

import java.util.ArrayList;

public class TestTodoListData {

  public static final TodoList TEST_TODO_1 = TodoList.builder().id(1L).build();

  public static final TodoList TEST_SAVE_TODO =
      TodoList.builder().id(2L).tasks(new ArrayList<>()).build();
}

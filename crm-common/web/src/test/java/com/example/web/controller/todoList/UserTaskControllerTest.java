package com.example.web.controller.todoList;

import com.example.mapper.task.TaskMapper;
import com.example.mapper.dto.task.TaskDto;
import com.example.web.controller.AbstractContextController;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static com.example.web.data.TestTaskData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WithMockUser(username = "test@gmail.com", authorities = "USER")
@Sql(scripts = {"classpath:/db/todoList/populate_todo_related_tables.sql"})
class UserTaskControllerTest extends AbstractContextController {

  @Autowired private TaskMapper taskMapper;

  @Autowired private ObjectMapper objectMapper;

  private static final String BASE_URL = "http://localhost:8080/manage/todo/tasks";

  @Test
  public void shouldReturnStatusCreatedAndSaveNewTaskProperly() throws Exception {

    log.info("Test saveTask() method");

    TaskDto taskDto = taskMapper.taskToTaskDto(TEST_SAVE_TASK);
    mockMvc
        .perform(
            post(BASE_URL + "/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto)))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(objectMapper.writeValueAsString(taskDto)))
        .andExpect(status().isCreated())
        .andReturn();
  }

  @Test
  public void shouldReturnStatusOkAndUpdateTaskProperly() throws Exception {

    log.info("Test updateTask() method");
    TaskDto taskDto = taskMapper.taskToTaskDto(TEST_UPDATE_TASK);
    mockMvc
        .perform(
            put(BASE_URL + "/task")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", String.valueOf(TEST_TASK_2.getId()))
                .content(objectMapper.writeValueAsString(taskDto)))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldReturnStatusOkAndGetTaskByIdProperly() throws Exception {

    log.info("Test getTaskById() method");

    TaskDto taskDto = taskMapper.taskToTaskDto(TEST_TASK_1);

    mockMvc
        .perform(get(BASE_URL + "/task/" + TEST_TASK_1.getId()))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(objectMapper.writeValueAsString(taskDto)))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldReturnStatusNoContentAndDeleteTaskByIdProperly() throws Exception {

    log.info("Test deleteTaskById() method");

    mockMvc
        .perform(delete(BASE_URL + "/task").param("id", String.valueOf(TEST_TASK_3.getId())))
        .andDo(print())
        .andExpect(status().isNoContent())
        .andReturn();
  }

  @Test
  public void shouldReturnStatusOkAndGetTasksWithoutFiltersProperly() throws Exception {

    log.info("Test getTasks() method");

    mockMvc
        .perform(get(BASE_URL))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldReturnStatusOkAndGetTasksWithFiltersProperly() throws Exception {

    log.info("Test getTasks() method");
    String[] filters = new String[] {"title"};
    mockMvc
        .perform(get(BASE_URL).param("filters", filters).param("query", "Create"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldReturnStatusOkAndGetActiveTasksProperly() throws Exception {

    log.info("Test getActiveTasks() method");

    mockMvc
        .perform(get(BASE_URL + "/active"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldReturnStatusOkAndGetTasksFromDateProperly() throws Exception {

    log.info("Test getTasksFromDate() method");

    String startDate = "2020-01-01 00:00:00";
    mockMvc
        .perform(get(BASE_URL + "/" + startDate))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldReturnStatusOkAndGetMissedTasksProperly() throws Exception {

    log.info("Test getMissedTasks() method");

    mockMvc
        .perform(get(BASE_URL + "/missed"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldReturnStatusOkAndGetTasksBetweenProperly() throws Exception {

    log.info("Test getTasksBetween() method");

    String start = "2021-01-01 12:00:00";
    String end = "2022-01-01 12:00:00";

    mockMvc
        .perform(get(BASE_URL + "/between").param("start", start).param("end", end))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }
}

package com.example.controller.todoList;

import com.example.TaskMapper;
import com.example.controller.AbstractContextController;
import com.example.dto.task.TaskDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static com.example.contactService.data.TestTaskData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Sql(scripts = {"classpath:/db/todoList/populate_todo_related_tables.sql"})
class UserTaskControllerTest extends AbstractContextController {

    @Autowired
    private TaskMapper taskMapper;

    private static final String BASE_URL = "http://localhost:8080/manage/tasks";

    @Test
    public void shouldReturnStatusCreatedAndSaveNewTaskProperly() throws Exception {

        log.info("Test saveTask() method");
        TaskDto saveDto = taskMapper.taskToTaskDto(TEST_SAVE_TASK);

        mockMvc.perform(post(BASE_URL + "/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(saveDto)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void shouldReturnStatusOkAndUpdateTaskProperly() throws Exception {

        log.info("Test updateTask() method");
        TaskDto updateTask = taskMapper.taskToTaskDto(TEST_UPDATE_TASK);
        String id = TEST_TASK_2.getId().toString();

        mockMvc.perform(put(BASE_URL + "/task").param("id", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateTask)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnStatusOkAndGetTaskByIdProperly() throws Exception {

        log.info("Test getTaskById() method");
        String id = TEST_TASK_1.getId().toString();

        mockMvc.perform(get(BASE_URL + "/task/" + id))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnStatusNoContentAndDeleteTaskByIdProperly() throws Exception {

        log.info("Test deleteTaskById() method");
        String id = TEST_TASK_4.getId().toString();

        mockMvc.perform(delete(BASE_URL + "/task").param("id", id))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    public void shouldReturnStatusOkAndGetTasksWithoutFiltersProperly() throws Exception {

        log.info("Test getTasks() method");

        mockMvc.perform(get(BASE_URL))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnStatusOkAndGetTasksWithFiltersProperly() throws Exception {

        log.info("Test getTasks() method");
        String[] filters = new String[] { "title", "description" };
        String query = "Create";

        mockMvc.perform(get(BASE_URL)
                .param("filters", filters)
                .param("query", query))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnStatusOkAndGetActiveTasksProperly() throws Exception {

        log.info("Test getActiveTasks() method");

        mockMvc.perform(get(BASE_URL + "/active"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnStatusOkAndGetTasksFromDateProperly() throws Exception {

        log.info("Test getTasksFromDate() method");
        String date = "2021-09-08 12:00:00";

        mockMvc.perform(get(BASE_URL + "/" + date))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnStatusOkAndGetMissedTasksProperly() throws Exception {

        log.info("Test getMissedTasks() method");

        mockMvc.perform(get(BASE_URL + "/missed"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnStatusOkAndGetTasksBetweenProperly() throws Exception {

        log.info("Test getTasksBetween() method");
        String start = "2021-09-01 00:00:00";
        String end = "2021-11-01 23:59:59";

        mockMvc.perform(get(BASE_URL + "/between")
                .param("start", start)
                .param("end", end))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
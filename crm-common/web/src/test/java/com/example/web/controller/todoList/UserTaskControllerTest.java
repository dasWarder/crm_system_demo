package com.example.web.controller.todoList;

import com.example.mapper.TaskMapper;
import com.example.web.controller.AbstractContextController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;


@Slf4j
//@WithMockUser(username = "test@gmail.com", authorities = "USER")
@Sql(scripts = {"classpath:/db/todoList/populate_todo_related_tables.sql"})
class UserTaskControllerTest extends AbstractContextController {

    @Autowired
    private TaskMapper taskMapper;

    private static final String BASE_URL = "http://localhost:8080/manage/tasks";

    @Test
    public void shouldReturnStatusCreatedAndSaveNewTaskProperly() throws Exception {

        log.info("Test saveTask() method");

    }

    @Test
    public void shouldReturnStatusOkAndUpdateTaskProperly() throws Exception {

        log.info("Test updateTask() method");

    }

    @Test
    public void shouldReturnStatusOkAndGetTaskByIdProperly() throws Exception {

        log.info("Test getTaskById() method");

    }

    @Test
    public void shouldReturnStatusNoContentAndDeleteTaskByIdProperly() throws Exception {

        log.info("Test deleteTaskById() method");

    }

    @Test
    public void shouldReturnStatusOkAndGetTasksWithoutFiltersProperly() throws Exception {

        log.info("Test getTasks() method");

    }

    @Test
    public void shouldReturnStatusOkAndGetTasksWithFiltersProperly() throws Exception {

        log.info("Test getTasks() method");

    }

    @Test
    public void shouldReturnStatusOkAndGetActiveTasksProperly() throws Exception {

        log.info("Test getActiveTasks() method");

    }

    @Test
    public void shouldReturnStatusOkAndGetTasksFromDateProperly() throws Exception {

        log.info("Test getTasksFromDate() method");

    }

    @Test
    public void shouldReturnStatusOkAndGetMissedTasksProperly() throws Exception {

        log.info("Test getMissedTasks() method");

    }

    @Test
    public void shouldReturnStatusOkAndGetTasksBetweenProperly() throws Exception {

        log.info("Test getTasksBetween() method");

    }
}
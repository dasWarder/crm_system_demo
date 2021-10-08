package com.example.web.controller.authority;

import com.example.service.user.authority.AuthorityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;


import static com.example.web.data.TestUserData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@Sql(scripts = { "classpath:/db/populate_todo_related_tables.sql"})
class SuperAdminAuthorityControllerTest extends  {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired private AuthorityService authorityService;

    private static final String BASE_URL = "http://localhost:8080/manage/leader/authorities";

    @Test
    public void shouldCreateAuthorityProperly() throws Exception {

        log.info("Test createAuthority() method of POST /authority endpoint");



    }

    @Test
    public void shouldUpdateAuthorityProperly() throws Exception {

        log.info("Test updateAuthority() method of PUT /authority endpoint");

    }

    @Test
    public void shouldDeleteAuthorityProperly() throws Exception {

        log.info("Test deleteAuthority() method of DELETE /authority endpoint");

    }

    @Test
    public void shouldGetAuthorityDetailsProperly() throws Exception {

        log.info("Test getAuthorityDetails() method of GET /authority/info endpoint");

    }

    @Test
    public void shouldGetAuthoritiesProperly() throws Exception {

        log.info("Test getAuthorities() method of GET endpoint");

    }
}
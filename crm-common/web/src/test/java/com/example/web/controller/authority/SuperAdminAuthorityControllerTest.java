package com.example.web.controller.authority;

import com.example.mapper.dto.authority.AuthorityDto;
import com.example.web.controller.AbstractContextController;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static com.example.web.data.TestAuthorityData.TEST_AUTHORITY_1;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WithMockUser(username = "leader@gmail.com", authorities = "SUPER_ADMIN")
@Sql(scripts = {"classpath:/db/todoList/populate_todo_related_tables.sql"})
class SuperAdminAuthorityControllerTest extends AbstractContextController {

  @Autowired private ObjectMapper objectMapper;

  private static final String BASE_URL = "http://localhost:8080/manage/leader/authorities";

  @Test
  public void shouldCreateAuthorityProperly() throws Exception {

    log.info("Test createAuthority() method of POST /authority endpoint");

    AuthorityDto dto = new AuthorityDto();
    dto.setAuthority("GROUP_MANAGER");

    mockMvc
        .perform(
            post(BASE_URL + "/authority")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andReturn();
  }

  @Test
  public void shouldUpdateAuthorityProperly() throws Exception {

    log.info("Test updateAuthority() method of PUT /authority endpoint");

    AuthorityDto dto = new AuthorityDto("GROUP_LEADER");
    mockMvc
        .perform(
            put(BASE_URL + "/authority")
                .param("updatedRole", TEST_AUTHORITY_1.getAuthority())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldDeleteAuthorityProperly() throws Exception {

    log.info("Test deleteAuthority() method of DELETE /authority endpoint");

    mockMvc
        .perform(delete(BASE_URL + "/authority").param("role", TEST_AUTHORITY_1.getAuthority()))
        .andDo(print())
        .andExpect(status().isNoContent())
        .andReturn();
  }

  @Test
  public void shouldGetAuthorityDetailsProperly() throws Exception {

    log.info("Test getAuthorityDetails() method of GET /authority/info endpoint");

    mockMvc
        .perform(get(BASE_URL + "/authority/info").param("role", TEST_AUTHORITY_1.getAuthority()))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldGetAuthoritiesProperly() throws Exception {

    log.info("Test getAuthorities() method of GET endpoint");

    mockMvc
        .perform(get(BASE_URL))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }
}

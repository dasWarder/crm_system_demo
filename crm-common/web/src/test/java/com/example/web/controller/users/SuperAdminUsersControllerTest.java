package com.example.web.controller.users;

import com.example.mapper.dto.user.superadmin.CreateFullUserDto;
import com.example.mapper.user.CreateUserMapper;
import com.example.mapper.user.UserMapper;
import com.example.service.user.authority.AuthorityService;
import com.example.web.controller.AbstractContextController;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static com.example.web.data.TestUserData.TEST_USER_1;
import static com.example.web.data.TestUserData.TEST_USER_2;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WithMockUser(username = "leader@gmail.com", authorities = "SUPER_ADMIN")
@Sql(
    scripts = {
      "classpath:/db/todoList/populate_todo_related_tables.sql",
      "classpath:/db/contactManager/populate_contacts_with_users.sql"
    })
class SuperAdminUsersControllerTest extends AbstractContextController {

  @Autowired private UserMapper userMapper;

  @Autowired private ObjectMapper objectMapper;

  @Autowired private AuthorityService authorityService;

  private static final String BASE_URL = "http://localhost:8080/manage/leader/users";

  @Test
  public void shouldSaveUserProperly() throws Exception {

    log.info("Test saveUser() method on POST /user endpoint");

    CreateFullUserDto createFullUserDto = userMapper.userToCreateFullUserDto(TEST_USER_1);
    createFullUserDto.setFirstName("Test");
    createFullUserDto.setLastName("Test");
    createFullUserDto.setJobTitle("test");
    createFullUserDto.setCompany("test");
    createFullUserDto.setMobilePhone("test");
    createFullUserDto.setCountry("Test");
    createFullUserDto.setEmail("tester@gmail.com");
    createFullUserDto.setRole("USER");

    mockMvc
        .perform(
            post(BASE_URL + "/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createFullUserDto)))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andReturn();
  }

  @Test
  public void shouldGetUserDetailsProperly() throws Exception {

    log.info("Test getUserDetails() method on GET /user endpoint");

    mockMvc
        .perform(get(BASE_URL + "/user").param("email", TEST_USER_1.getEmail()))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();
  }

  @Test
  public void shouldUpdateUserRoleProperly() throws Exception {

    log.info("Test updateUserRole() method on PUT /user/role endpoint");

    mockMvc
        .perform(
            put(BASE_URL + "/user/role")
                .param("email", TEST_USER_1.getEmail())
                .param("role", "ADMIN"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldDeleteUserProperly() throws Exception {

    log.info("Test deleteUser() method on DELETE /user endpoint");

    mockMvc
        .perform(delete(BASE_URL + "/user").param("email", TEST_USER_2.getEmail()))
        .andDo(print())
        .andExpect(status().isNoContent())
        .andReturn();
  }

  @Test
  public void shouldGetUsersProperly() throws Exception {

    log.info("Test getUsers() method on GET endpoint");

    mockMvc
        .perform(get(BASE_URL))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldGetUsersByParamProperly() throws Exception {

    log.info("Test getUsersByParam() method on GET /filter endpoint");

    mockMvc
        .perform(get(BASE_URL + "/filter").param("param", "email").param("query", "te"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }
}

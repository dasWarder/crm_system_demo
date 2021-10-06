package com.example.web.controller.users;

import com.example.exception.AuthorityNotFoundException;
import com.example.mapper.dto.user.admin.CreateUserDto;
import com.example.mapper.user.UserMapper;
import com.example.model.user.User;
import com.example.model.user.UserAuthority;
import com.example.service.user.authority.AuthorityService;
import com.example.web.controller.AbstractContextController;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static com.example.web.data.TestContactData.TEST_SAVE_CONTACT;
import static com.example.web.data.TestUserData.TEST_SAVE_USER;
import static com.example.web.data.TestUserData.TEST_USER_1;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WithMockUser(username = "test@gmail.com", authorities = "ADMIN")
@Sql(
    scripts = {
      "classpath:/db/todoList/populate_todo_related_tables.sql",
      "classpath:/db/contactManager/populate_contacts_with_users.sql"
    })
class AdminUsersControllerTest extends AbstractContextController {

  @Autowired private UserMapper userMapper;

  @Autowired private ObjectMapper objectMapper;

  @Autowired private AuthorityService authorityService;

  private static final String BASE_URL = "http://localhost:8080/manage/admin/users";

  @Test
  public void shouldCreateUserProperly() throws Exception, AuthorityNotFoundException {

    log.info("Test createUser() method on POST /user endpoint");
    User storeUser = TEST_SAVE_USER;
    UserAuthority user = authorityService.getUserAuthorityByAuthorityName("USER");
    storeUser.setRole(user);
    CreateUserDto userDto = userMapper.userToCreateUserDto(TEST_SAVE_CONTACT, storeUser);
    mockMvc
        .perform(
            post(BASE_URL + "/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andReturn();
  }

  @Test
  public void shouldGetUsersDetailsProperly() throws Exception {

    log.info("Test getUsersDetails() method on GET /user endpoint");

    mockMvc
        .perform(get(BASE_URL + "/user").param("email", TEST_USER_1.getEmail()))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldGetUsersProperly() throws Exception {

    log.info("Test getUsers() method on GET endpoint");

    mockMvc
        .perform(get(BASE_URL))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldGetUsersByParamProperly() throws Exception {

    log.info("Test getUsersByParam() method on GET /filter endpoint");

    mockMvc
        .perform(get(BASE_URL + "/filter").param("param", "email").param("query", "test"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }


  @Test
  public void shouldChangeUserRoleProperly() throws Exception {

    log.info("Test changeUserRole() method on PUT /user/role endpoint");

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
}

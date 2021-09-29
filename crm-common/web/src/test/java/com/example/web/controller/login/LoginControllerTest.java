package com.example.web.controller.login;

import com.example.mapper.UserMapper;
import com.example.mapper.dto.user.AuthUserDto;
import com.example.mapper.dto.user.BaseUserDto;
import com.example.mapper.dto.user.SaveUserDto;
import com.example.mapper.dto.user.token.TokenRefreshRequest;
import com.example.web.controller.AbstractContextController;
import com.example.web.data.TestContactData;
import com.example.web.data.TestTokenData;
import com.example.web.data.TestUserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static com.example.web.data.TestContactData.TEST_CONTACT_1;
import static com.example.web.data.TestContactData.TEST_SAVE_CONTACT;
import static com.example.web.data.TestTokenData.*;
import static com.example.web.data.TestUserData.TEST_SAVE_USER;
import static com.example.web.data.TestUserData.TEST_USER_1;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Sql(
    scripts = {
      "classpath:/db/todoList/populate_todo_related_tables.sql",
      "classpath:/db/token/populate_password_reset_token_table.sql",
      "classpath:/db/token/populate_refresh_token_table.sql"
    })
class LoginControllerTest extends AbstractContextController {

  @Autowired private ObjectMapper objectMapper;

  @Autowired private UserMapper userMapper;

  private static final String BASE_URL = "http://localhost:8080/login";

  @Test
  public void shouldRegistrationNewUserProperly() throws Exception {

    log.info("Test registrationNewUser() method for POST /registration/common endpoint");

    SaveUserDto userDto = userMapper.userToSaveUserDto(TEST_SAVE_CONTACT, TEST_SAVE_USER);
    BaseUserDto responseDto = userMapper.userToBaseUserDto(TEST_SAVE_USER);

    mockMvc
        .perform(
            post(BASE_URL + "/registration/common")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(objectMapper.writeValueAsString(responseDto)))
        .andExpect(status().isCreated())
        .andReturn();
  }

  @Test
  public void shouldRefreshTokenProperly() throws Exception {

    log.info("Test refreshToken() method of POST /refresh endpoint");

    TokenRefreshRequest dto = new TokenRefreshRequest(TEST_TOKEN_1.getToken());
    mockMvc
        .perform(
            post(BASE_URL + "/refresh")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }
}

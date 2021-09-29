package com.example.web.controller.profile;

import com.example.mapper.ResetTokenMapper;
import com.example.mapper.TokenMapper;
import com.example.mapper.dto.user.BaseUserDto;
import com.example.mapper.dto.user.ResetPasswordDto;
import com.example.mapper.dto.user.token.ResetTokenDto;
import com.example.model.user.Token;
import com.example.web.controller.AbstractContextController;
import com.example.web.data.TestTokenData;
import com.example.web.data.TestUserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static com.example.web.data.TestTokenData.TEST_TOKEN_1;
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
class ResetPasswordControllerTest extends AbstractContextController {

  @Autowired private TokenMapper tokenMapper;

  @Autowired private ResetTokenMapper resetTokenMapper;

  @Autowired private ObjectMapper objectMapper;

  private static final String BASE_URL = "http://localhost:8080/login/forget";

  @Test
  public void shouldProcessForgotPasswordProperly() throws Exception {

    log.info("Test processForgotPassword() method with the base url POST endpoint path");

    BaseUserDto dto = new BaseUserDto(TEST_USER_1.getEmail());

    mockMvc
        .perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
        .andDo(print())
        .andExpect(status().isNoContent())
        .andReturn();
  }

  @Test
  public void shouldResetTokenValidationProperly() throws Exception {

    log.info("Test resetTokenValidation() method for /reset endpoint");

    ResetTokenDto dto = new ResetTokenDto(TEST_TOKEN_1.getToken());
    mockMvc
        .perform(get(BASE_URL + "/reset").param("token", TEST_TOKEN_1.getToken()))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldResetPasswordProperly() throws Exception {

    log.info("Test resetPassword() method for POST /reset endpoint");

    ResetPasswordDto dto = new ResetPasswordDto("1234567", "1234567", TEST_TOKEN_1.getToken());
    BaseUserDto userDto = new BaseUserDto(TEST_TOKEN_1.getSubject());
    mockMvc
        .perform(
            put(BASE_URL + "/reset")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(objectMapper.writeValueAsString(userDto)))
        .andExpect(status().isOk())
        .andReturn();
  }
}

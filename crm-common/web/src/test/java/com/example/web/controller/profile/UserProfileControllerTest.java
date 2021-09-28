package com.example.web.controller.profile;

import com.example.mapper.UserMapper;
import com.example.mapper.UserMapperWithAuthority;
import com.example.mapper.dto.contact.ContactDto;
import com.example.mapper.dto.contact.SaveContactDto;
import com.example.mapper.dto.user.UpdateUserEmailDto;
import com.example.mapper.dto.user.UpdateUserPasswordDto;
import com.example.web.controller.AbstractContextController;
import com.example.web.data.TestUserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static com.example.web.data.TestUserData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WithMockUser(username = "test@gmail.com", authorities = "USER")
@Sql(
    scripts = {
      "classpath:/db/todoList/populate_todo_related_tables.sql",
      "classpath:/db/contactManager/populate_contacts_with_users.sql"
    })
class UserProfileControllerTest extends AbstractContextController {

  @Autowired private ObjectMapper objectMapper;

  private static final String BASE_URL = "http://localhost:8080/manage/profile";

  @Test
  public void shouldReturnStatusOkAndGetUserProfileProperly() throws Exception {

    log.info("Test getUserProfile() method for /manage/profile endpoint");

    mockMvc
        .perform(get(BASE_URL))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldUpdateContactInformationProperly() throws Exception {

    log.info("Test updateContactInformation() method for /manage/profile/contact endpoint");

    SaveContactDto dto =
        new SaveContactDto(
            1L, "Update", "Update", "Software Engineer", "iTechArt", "Belarus", "+37234819451");

    mockMvc
        .perform(
            put(BASE_URL + "/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldUpdateUserPasswordProperly() throws Exception {

    log.info("Test updateUserPassword() method for /manage/profile/main/password endpoint");

    UpdateUserPasswordDto dto = new UpdateUserPasswordDto("12345", "1234567");
    mockMvc
        .perform(
            put(BASE_URL + "/main/password")
                .param("email", TEST_USER_1.getEmail())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldUpdateUserEmailProperly() throws Exception {

    log.info("Test updateUserEmail() method for /manage/profile/main/email endpoint");

    UpdateUserEmailDto dto = new UpdateUserEmailDto(TEST_USER_1.getEmail(), "update@gmail.com");
    mockMvc
        .perform(
            put(BASE_URL + "/main/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }
}

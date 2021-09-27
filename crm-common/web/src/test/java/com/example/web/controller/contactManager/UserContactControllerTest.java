package com.example.web.controller.contactManager;

import com.example.web.controller.AbstractContextController;
import com.example.web.data.TestData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static com.example.web.data.TestContactData.TEST_CONTACT_1;
import static com.example.web.data.TestData.WRONG_EMAIL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WithMockUser(username = "test@gmail.com", authorities = "USER")
@Sql(scripts = {"classpath:db/contactManager/populate_contact.sql"})
public class UserContactControllerTest extends AbstractContextController {

  private static final String BASE_URL = "http://localhost:8080/manage/contacts";

  @Test
  public void shouldReturnStatusOkAndPageOfAllContactsProperly() throws Exception {

    log.info("Test findAllContacts() method");
    mockMvc
        .perform(get(BASE_URL))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldReturnStatusOkAndGetContactByEmailProperly() throws Exception {

    log.info("Test getContactByEmail() method");

    mockMvc
        .perform(get(BASE_URL + "/contact").param("email", TEST_CONTACT_1.getEmail()))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldReturnStatusAndThrowExceptionWhenGetContactByEmailWithWrongEmail() throws Exception {

    log.info("Test getContactByEmail() method with a wrong email");

    mockMvc.perform(get(BASE_URL + "/contact").param("email", WRONG_EMAIL))
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andReturn();
  }
}

package com.example.controller.contactManager;

import com.example.mapper.ContactMapper;
import com.example.controller.AbstractContextController;
import com.example.mapper.dto.contact.ContactDto;
import com.example.mapper.dto.contact.SaveContactDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static com.example.contactService.data.TestContactData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Sql(scripts = {"classpath:db/contactManager/populate_contact.sql"})
public class AdminContactControllerTest extends AbstractContextController {

    @Autowired
    private ContactMapper contactMapper;

    private static final String BASE_URL = "http://localhost:8080/admin/manage/contacts";

    @Test
    public void shouldReturnStatusOkAndPageOfAllContactsProperly() throws Exception {

        log.info("Test findAllContacts() method");

        mockMvc.perform(get(BASE_URL))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnStatusOkAndPageOfAllContactsFilteredByParamProperly() throws Exception {

        log.info("Test findAllByParam() method");

        mockMvc.perform(get(BASE_URL)
                        .param("filteredBy", "fullname")
                        .param("query", "Mar"))
                        .andDo(print())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    public void shouldReturnStatusIsCreatedAndSaveContactProperly() throws Exception {

        log.info("Test saveContact() method");

        SaveContactDto saveContactDto = contactMapper.contactToSaveContactDto(TEST_SAVE_CONTACT);
        ContactDto contactDto = contactMapper.contactToContactDto(TEST_SAVE_CONTACT);

        mockMvc.perform(post(BASE_URL + "/contact")
                .content(objectMapper.writeValueAsString(saveContactDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(contactDto)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void shouldReturnStatusOkAndUpdateContactProperly() throws Exception {

        log.info("Test updateContactByEmail() method");

        String email = TEST_CONTACT_3.getEmail();
        SaveContactDto updateContactDto = contactMapper.contactToSaveContactDto(TEST_UPDATE_CONTACT);
        ContactDto responseContactDto = contactMapper.contactToContactDto(TEST_UPDATE_CONTACT);

        mockMvc.perform(put(BASE_URL + "/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .param("email", email)
                .content(objectMapper.writeValueAsString(updateContactDto)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseContactDto)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnStatusOkAndGetContactByEmailProperly() throws Exception {

        log.info("Test getContactByEmail() method");

        String email = TEST_CONTACT_1.getEmail();
        ContactDto responseDto = contactMapper.contactToContactDto(TEST_CONTACT_1);

        mockMvc.perform(get(BASE_URL + "/contact")
                .param("email", email))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnStatusOkAndDeleteContactByEmailProperly() throws Exception {

        log.info("Test deleteContactByEmail() method");

        String email = TEST_CONTACT_5.getEmail();

        mockMvc.perform(delete(BASE_URL + "/contact")
                .param("email", email))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}

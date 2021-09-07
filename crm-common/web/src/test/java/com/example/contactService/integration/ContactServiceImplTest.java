package com.example.contactService.integration;


import com.example.AbstractTest;
import com.example.contactManager.Contact;
import com.example.exception.ContactNotFoundException;
import com.example.service.contact.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.contactService.data.TestContactData.*;
import static com.example.contactService.data.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Sql(scripts = {"classpath:/db/contact/populate_contact.sql"})
public class ContactServiceImplTest extends AbstractTest {

    @Autowired
    private ContactService contactService;

    @Test
    public void shouldFindAllContactsProperly() {

        log.info("Test findAllContacts() method of the Contact service class");
        List<Contact> expectedContacts = Stream.of(TEST_CONTACT_1, TEST_CONTACT_2,
                                                TEST_CONTACT_3, TEST_CONTACT_4, TEST_CONTACT_5)
                                                .collect(Collectors.toList());
        PageImpl<Contact> expectedPage = new PageImpl<>(expectedContacts);

        Page<Contact> actualContactsPage = contactService.findAllContacts(TEST_PAGEABLE);

        Assertions.assertNotNull(actualContactsPage.getContent());
        assertThat(actualContactsPage.getContent()).usingRecursiveComparison()
                                        .isEqualTo(expectedPage.getContent());
    }

    @Test
    public void shouldFindAllByFilterParamProperly() {

        log.info("Test findAllContactsByParam() method of the Contact service class");
        List<Contact> sortedByFullName = Stream.of(TEST_CONTACT_3, TEST_CONTACT_4)
                                                        .collect(Collectors.toList());
        PageImpl<Contact> expectedPage = new PageImpl<>(sortedByFullName);

        Page<Contact> actualPage = contactService.findAllByParam("fullname", "Mar", TEST_PAGEABLE);

        Assertions.assertNotNull(actualPage.getContent());
        assertThat(actualPage.getContent()).usingRecursiveComparison()
                                            .isEqualTo(expectedPage.getContent());
    }

    @Test
    public void shouldSaveContactProperly() {

        log.info("Test saveContact() method of the Contact service class");
        Contact store = TEST_SAVE_CONTACT;

        Contact storedContact = contactService.saveContact(store);

        Assertions.assertNotNull(storedContact);
        assertThat(store).usingRecursiveComparison()
                                .isEqualTo(storedContact);
    }

    @Test
    public void shouldUpdateContactByEmailProperly() throws ContactNotFoundException {

        log.info("Test updateContactByEmail() method of the Contact service class");

        Contact updatedContact = contactService.updateContactByEmail(TEST_CONTACT_3.getEmail(), TEST_UPDATE_CONTACT);

        Assertions.assertNotNull(updatedContact);
        assertThat(updatedContact).usingRecursiveComparison()
                                        .isEqualTo(TEST_UPDATE_CONTACT);
    }

    @Test
    public void shouldThrowExceptionIfUpdateContactByEmailWithWrongEmail() {

        log.info("Test updateContactByEmail() method throw an exception when an email is wrong");

        Assertions.assertThrows(ContactNotFoundException.class,
                () -> contactService.updateContactByEmail(WRONG_EMAIL, TEST_UPDATE_CONTACT));
    }

    @Test
    public void shouldGetContactByIdProperly() throws ContactNotFoundException {

        log.info("Test getContactById() method of the Contact service class");

        Contact getContactById = contactService.getContactById(TEST_CONTACT_1.getId());

        Assertions.assertNotNull(getContactById);
        assertThat(getContactById).usingRecursiveComparison()
                                    .isEqualTo(TEST_CONTACT_1);
    }

    @Test
    public void shouldThrowExceptionIfGetContactByIdWithWrongId() {

        log.info("Test getContactById() method throw an exception when id is wrong");

        Assertions.assertThrows(ContactNotFoundException.class,
                                                        () -> contactService.getContactById(WRONG_ID));
    }

    @Test
    public void shouldGetContactByEmailProperly() throws ContactNotFoundException {

        log.info("Test getContactByEmail() method of the Contact service class");

        Contact contactByEmail = contactService.getContactByEmail(TEST_CONTACT_1.getEmail());

        Assertions.assertNotNull(contactByEmail);
        assertThat(contactByEmail).usingRecursiveComparison()
                                    .isEqualTo(TEST_CONTACT_1);
    }

    @Test
    public void shouldThrowExceptionIfGetContactByEmailWithWrongEmail() {

        log.info("Test getContactByEmail() method throw an exception when an email is wrong");

        Assertions.assertThrows(ContactNotFoundException.class,
                                        () -> contactService.getContactByEmail(WRONG_EMAIL));
    }

    @Test
    public void shouldDeleteContactByEmailProperly() {

        log.info("Test deleteContactByEmail() method of the Contact service class");

        contactService.deleteContactByEmail(TEST_CONTACT_5.getEmail());

        Assertions.assertThrows(ContactNotFoundException.class,
                                    () -> contactService.getContactByEmail(TEST_CONTACT_5.getEmail()));
    }

}

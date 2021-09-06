package com.example.service;


import com.example.Contact;
import com.example.exception.ContactNotFoundException;
import com.example.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.service.ContactTestData.*;
import static org.mockito.Mockito.when;

@Slf4j
class ContactServiceImplTest {

    private final ContactRepository contactRepository = Mockito.mock(ContactRepository.class);

    private final ContactService contactService = new ContactServiceImpl(contactRepository);

    @Test
    public void shouldFindAllContactAndReturnPageProperly() {

        log.info("Test findAll() method of the Contact service class");
        List<Contact> contactList = Stream.of(TEST_CONTACT_1, TEST_CONTACT_2, TEST_CONTACT_3)
                                                                    .collect(Collectors.toList());
        PageImpl<Contact> contacts = new PageImpl<>(contactList);

        when(contactRepository.findAll(TEST_PAGEABLE))
                                        .thenReturn(contacts);

        Page<Contact> contactResponseList = contactService.findAllContacts(TEST_PAGEABLE);

        Assertions.assertNotNull(contactResponseList);
        Assertions.assertEquals(contacts, contactResponseList);
    }

    @Test
    public void shouldSaveContactProperly() {

        log.info("Test saveContact() method of the Contact service class");
        when(contactRepository.save(TEST_CONTACT_1)).thenReturn(TEST_CONTACT_1);

        Contact storedContact = contactService.saveContact(TEST_CONTACT_1);

        Assertions.assertNotNull(storedContact);
        Assertions.assertEquals(TEST_CONTACT_1, storedContact);
    }

    @Test
    public void shouldUpdateContactByEmailProperly() throws ContactNotFoundException {

        log.info("Test updateContactByEmail() method of the Contact service class");
        when(contactRepository.getContactByEmail(TEST_CONTACT_1.getEmail())).thenReturn(Optional.of(TEST_CONTACT_1));
        when(contactRepository.save(TEST_UPDATE_CONTACT)).thenReturn(TEST_UPDATE_CONTACT);

        Contact updatedByEmail = contactService.updateContactByEmail(TEST_CONTACT_1.getEmail(), TEST_UPDATE_CONTACT);

        Assertions.assertNotNull(updatedByEmail);
        Assertions.assertEquals(TEST_UPDATE_CONTACT, updatedByEmail);
    }

    @Test
    public void shouldThrowExceptionIfContactWithEmailNotFoundOrNull() throws ContactNotFoundException {

        log.info("Test updateContactByEmail() method, that must throw an exception when an input param is null");
        Assertions.assertThrows(ContactNotFoundException.class,
                () -> contactService.updateContactByEmail(TEST_CONTACT_1.getEmail(), TEST_UPDATE_CONTACT));
    }

    @Test
    public void shouldGetContactByIdProperly() throws ContactNotFoundException {

        log.info("Test getContactById() method of the Contact service class");
        when(contactRepository.findById(TEST_CONTACT_1.getId())).thenReturn(Optional.of(TEST_CONTACT_1));

        Contact contactById = contactService.getContactById(TEST_CONTACT_1.getId());

        Assertions.assertNotNull(contactById);
        Assertions.assertEquals(TEST_CONTACT_1, contactById);
    }

    @Test
    public void shouldThrowExceptionIfContactIdIsWrongOrNull() {

        log.info("Test getContactById() method, that must throw an exception when an input param is wrong or null");

        Assertions.assertThrows(ContactNotFoundException.class, () -> contactService.getContactById(100L));
    }

    @Test
    public void shouldGetContactByEmailProperly() throws ContactNotFoundException {

        log.info("Test getContactByEmail() method of the Contact service class");
        when(contactRepository.getContactByEmail(TEST_CONTACT_2.getEmail())).thenReturn(Optional.of(TEST_CONTACT_2));

        Contact contactByEmail = contactService.getContactByEmail(TEST_CONTACT_2.getEmail());

        Assertions.assertNotNull(contactByEmail);
        Assertions.assertEquals(TEST_CONTACT_2, contactByEmail);
    }

    @Test
    public void shouldThrowExceptionIfGetContactByEmailWithWrongOrNullEmail() {

        log.info("Test getContactByEmail() method, that throw an exception when email is wrong or null");

        Assertions.assertThrows(ContactNotFoundException.class, () -> contactService.getContactByEmail("wrong@gmail.com"));
    }

    @Test
    public void shouldDeleteContactByEmailProperly() {

        log.info("Test deleteContactByEmail() method of the Contact service class");

        contactService.deleteContactByEmail(TEST_CONTACT_2.getEmail());
    }

}
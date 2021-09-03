package com.example.service;

import com.example.Contact;
import com.example.exception.ContactNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ContactService {

    Contact saveContact(Contact contact);

    Contact updateContact(Long id, Contact contact) throws ContactNotFoundException;

    Contact updateContactByEmail(String email, Contact contact) throws ContactNotFoundException;

    Contact getContactById(Long id) throws ContactNotFoundException;

    Contact getContactByEmail(String email) throws ContactNotFoundException;

    Contact getContactByMobilePhone(String phoneNumber) throws ContactNotFoundException;

    void deleteContactById(Long id);

    void deleteContactByEmail(String email);

    Page<Contact> findAllContacts(Pageable pageable);

    Page<Contact> findAllByParam(final String filteredBy, final String query, final Pageable pageable);

    Page<Contact> getAllContactsByCompany(String company, Pageable pageable);

    Page<Contact> getAllContactsByJobTitle(String jobTitle, Pageable pageable);
}

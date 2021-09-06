package com.example.service;

import com.example.Contact;
import com.example.exception.ContactNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {

    Contact saveContact(Contact contact);

    Contact updateContactByEmail(String email, Contact contact) throws ContactNotFoundException;

    Contact getContactById(Long id) throws ContactNotFoundException;

    Contact getContactByEmail(String email) throws ContactNotFoundException;

    void deleteContactByEmail(String email);

    Page<Contact> findAllContacts(Pageable pageable);

    Page<Contact> findAllByParam(final String filteredBy, final String query, final Pageable pageable);
}

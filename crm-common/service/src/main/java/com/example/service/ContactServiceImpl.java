package com.example.service;

import com.example.Contact;
import com.example.exception.ContactNotFoundException;
import com.example.repository.ContactRepository;
import com.example.service.specification.ContactSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.service.specification.ContactSpecification.findContactsByParam;


@Slf4j
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Contact> findAllContacts(Pageable pageable) {

        log.info("Receiving a page for all contacts");
        Page<Contact> allContacts = contactRepository.findAll(pageable);

        return allContacts;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Contact> findAllByParam(String filteredBy, String query, Pageable pageable) {

        log.info("Receiving a page with filtering by {} contacts", filteredBy);

        switch (filteredBy.toLowerCase().trim()) {

            case "fullname": {

                String[] slicedFullName = query.split(" ");
                int fullNameLength = slicedFullName.length;

                String firstName = fullNameLength > 0? slicedFullName[0] : "";
                String lastName = fullNameLength > 1? slicedFullName[fullNameLength - 1] : "";

                Page<Contact> contactsByFullname = contactRepository.findAll(
                        ContactSpecification
                                .findContactsByFullName(firstName, lastName), pageable);

                return contactsByFullname;
            } default: {

                Page<Contact> contactsByParam = contactRepository.findAll(
                                                            findContactsByParam(filteredBy, query), pageable);

                return contactsByParam;
            }
        }
    }

    @Override
    @Transactional
    public Contact saveContact(Contact contact) {

        log.info("Storing a new contact");
        Contact storedContact = contactRepository.save(contact);

        return storedContact;
    }

    @Override
    @Transactional
    public Contact updateContactByEmail(String email, Contact contact) throws ContactNotFoundException {

        log.info("Updating a contact by its email = {}", email);
        Contact validContactByEmail = contactRepository.getContactByEmail(email)
                                                    .orElseThrow(() -> new ContactNotFoundException(
                                                            String.format("The contact with email = %s not found", email)));
        contact.setId(validContactByEmail.getId());
        Contact updatedContact = contactRepository.save(contact);

        return updatedContact;
    }

    @Override
    @Transactional(readOnly = true)
    public Contact getContactById(Long id) throws ContactNotFoundException {

        log.info("Receiving a contact by its id = {}", id);
        Contact receivedContactById = contactRepository.findById(id)
                                                    .orElseThrow(() -> new ContactNotFoundException(
                                                            String.format("A contact with id=%d not found", id)));
        return receivedContactById;
    }

    @Override
    @Transactional(readOnly = true)
    public Contact getContactByEmail(String email) throws ContactNotFoundException {

        log.info("Receiving a contact by its email = {}", email);
        Contact validContactByEmail = contactRepository.getContactByEmail(email)
                                                .orElseThrow(() -> new ContactNotFoundException(
                                                        String.format("The contact with email = %s not found", email)));
        return validContactByEmail;
    }

    @Override
    @Transactional
    public void deleteContactByEmail(String email) {

        log.info("Removing a contact with email");
        contactRepository.deleteContactByEmail(email);
    }

}

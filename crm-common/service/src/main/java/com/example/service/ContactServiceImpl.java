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
    public Page<Contact> findAllByParam(String filteredBy, String query, Pageable pageable) {

        log.info("Receiving a page with filtering by {} contacts", filteredBy);

        switch (filteredBy.toLowerCase().trim()) {

            case "fullname": {

                String[] slicedFullName = query.split(" ");
                int fullNameLength = slicedFullName.length;

                String firstName = fullNameLength > 0? slicedFullName[0] : "";
                String lastName = fullNameLength > 1? slicedFullName[fullNameLength - 1] : "";

                return contactRepository.findAll(
                        ContactSpecification
                                .findContactsByFullName(firstName, lastName), pageable);
            } default: {

                return contactRepository.findAll(
                        ContactSpecification
                                .findContactsByParam(filteredBy, query), pageable);
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
    public Contact updateContact(Long id, Contact contact) throws ContactNotFoundException {

        log.info("Updating a contact with id = {}", id);
        Contact databaseContact = contactRepository.findById(id)
                                                    .orElseThrow(() -> new ContactNotFoundException(
                                                            String.format("A contact with id=%d not found", id)));
        contact.setId(databaseContact.getId());
        Contact updatedContact = contactRepository.save(contact);

        return updatedContact;
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
    @Transactional(readOnly = true)
    public Contact getContactByMobilePhone(String phoneNumber) throws ContactNotFoundException {

        log.info("Receiving a contact by its mobile phone number = {}", phoneNumber);
        Contact validContactByPhone = contactRepository.getContactByMobilePhone(phoneNumber)
                                                .orElseThrow(() -> new ContactNotFoundException(
                                                     String.format("The contact with email = %s not found", phoneNumber)));
        return validContactByPhone;
    }

    @Override
    @Transactional
    public void deleteContactById(Long id) {

        log.info("Removing a contact by its id = {}", id);
        contactRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteContactByEmail(String email) {

        log.info("Removing a contact with email");
        contactRepository.deleteContactByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Contact> getAllContactsByCompany(String company, Pageable pageable) {

        log.info("Receiving a page of contacts by a company = {}", company);
        Page<Contact> contactsByCompany = contactRepository.getAllByCompany(company, pageable);

        return contactsByCompany;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Contact> getAllContactsByJobTitle(String jobTitle, Pageable pageable) {

        log.info("Receiving a page of contacts by a job title = {}", jobTitle);
        Page<Contact> contactsByJob = contactRepository.getAllByJobTitle(jobTitle, pageable);

        return contactsByJob;
    }
}

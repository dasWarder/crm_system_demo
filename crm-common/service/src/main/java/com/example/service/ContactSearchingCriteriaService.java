package com.example.service;

import com.example.Contact;
import com.example.repository.ContactRepository;
import com.example.service.specification.ContactSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactSearchingCriteriaService implements ContactSearchingService {

    private final ContactRepository contactRepository;

    @Override
    public Page<Contact> findAllByFullName(final String firstName, final String lastName, final Pageable pageable) {

        Page<Contact> filteredByFullName = contactRepository.findAll(
                        ContactSpecification.findContactByFullName(firstName, lastName), pageable);

        return filteredByFullName;
    }
}

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
    public Page<Contact> findAllByParam(String filteredBy, String query, Pageable pageable) {

        Page<Contact> filteredByParam = null;

        switch (filteredBy.toLowerCase().trim()) {

            case "fullname": {
                String[] slicedFullName = query.split(" ");
                int fullNameLength = slicedFullName.length;

                String firstName = fullNameLength > 0? slicedFullName[0] : "";
                String lastName = fullNameLength > 1? slicedFullName[fullNameLength - 1] : "";

                filteredByParam = contactRepository.findAll(
                                                          ContactSpecification
                                                                .findContactsByFullName(firstName, lastName), pageable);
                break;
            } default: {
                filteredByParam = contactRepository.findAll(
                                                        ContactSpecification
                                                                .findContactsByParam(filteredBy, query), pageable);
                break;
            }
        }

        return filteredByParam;
    }
}

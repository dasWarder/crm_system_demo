package com.example.controller.contactManager;


import com.example.Contact;
import com.example.ContactMapper;
import com.example.dto.ContactDto;
import com.example.exception.ContactNotFoundException;
import com.example.service.ContactSearchingService;
import com.example.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AbstractContactController {

    protected final ContactMapper contactMapper;

    protected final ContactService contactService;

    protected final ContactSearchingService searchingService;

    protected ContactDto receiveContactByEmail(String email) throws ContactNotFoundException {

        Contact contactByEmail = contactService.getContactByEmail(email);
        ContactDto responseContactDto = contactMapper.contactToContactDto(contactByEmail);

        return responseContactDto;
    }

    protected Page<ContactDto> receiveContacts(Pageable pageable) {

        Page<ContactDto> responseContacts = contactService.findAllContacts(pageable)
                                                            .map(contactMapper::contactToContactDto);
        return responseContacts;
    }

    protected Page<ContactDto> receiveCompanyContacts(Pageable pageable, String company) {

        Page<ContactDto> responseContactsByCompany = contactService.getAllContactsByCompany(company, pageable)
                                                                    .map(contactMapper::contactToContactDto);
        return responseContactsByCompany;
    }

    protected Page<ContactDto> receiveJobContacts(Pageable pageable, String jobName) {

        Page<ContactDto> responseContactsByJobName = contactService.getAllContactsByJobTitle(jobName, pageable)
                                                                    .map(contactMapper::contactToContactDto);
        return responseContactsByJobName;
    }

    protected Page<ContactDto> receiveCriteriaContact(String filterBy, String query, Pageable pageable) {

        Page<Contact> filteredContacts = null;

        switch (filterBy.toLowerCase().trim()) {
            
            case "fullName": {

                String[] slicedFullName = query.split(" ");
                int fullNameLength = slicedFullName.length;
                filteredContacts = searchingService.findAllByFullName(fullNameLength == 0 ? "" : slicedFullName[0],
                                                                fullNameLength == 1 ? "" : slicedFullName[1], pageable);
                break;
            }
        }

        Page<ContactDto> responseContactsDto = filteredContacts.map(contactMapper::contactToContactDto);

        return responseContactsDto;
    }
}

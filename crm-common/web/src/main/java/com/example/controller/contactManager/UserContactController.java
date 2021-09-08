package com.example.controller.contactManager;

import com.example.ContactMapper;
import com.example.ContactMapperWithUser;
import com.example.contactManager.Contact;
import com.example.dto.contact.ContactDto;
import com.example.exception.ContactNotFoundException;
import com.example.service.contact.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manage/contacts")
public class UserContactController {

    protected final ContactMapper contactMapper;

    protected final ContactService contactService;

    protected final ContactMapperWithUser customMapper;

    @GetMapping("/contact")
    public ResponseEntity<ContactDto> getContactByEmail(@RequestParam("email") String email)
                                                                                throws ContactNotFoundException {
        Contact contactByEmail = contactService.getContactByEmail(email);
        ContactDto responseContactDto = contactMapper.contactToContactDto(contactByEmail);

        return ResponseEntity.ok(responseContactDto);
    }

    @GetMapping
    public ResponseEntity<Page<ContactDto>> getAllContacts(@RequestParam(value = "filteredBy", required = false) String filteredBy,
                                                           @RequestParam(value = "query", required = false) String query,
                                                           @PageableDefault(size = 20, sort = "id") Pageable pageable) {

        Page<Contact> contacts = Objects.isNull(filteredBy) ?
                                                    contactService.findAllContacts(pageable) :
                                                    contactService.findAllByParam(filteredBy, query, pageable);
        Page<ContactDto> responseContacts = contacts.map(contactMapper::contactToContactDto);

        return ResponseEntity.ok(responseContacts);
    }
}

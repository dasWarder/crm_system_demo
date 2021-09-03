package com.example.controller;

import com.example.Contact;
import com.example.ContactMapper;
import com.example.dto.ContactDto;
import com.example.dto.SaveContactDto;
import com.example.exception.ContactNotFoundException;
import com.example.service.ContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/admin/manage/contacts")
public class ContactController extends AbstractContactController {

    private static final String BASE_URL = "http://localhost:8080/admin/manage/contacts";

    public ContactController(ContactMapper contactMapper, ContactService contactService) {
        super(contactMapper, contactService);
    }

    @PostMapping("/contact")
    public ResponseEntity<ContactDto> saveContact(@RequestBody SaveContactDto contactDto) {

        Contact requestContact = contactMapper.saveContactDtoToContact(contactDto);
        Contact storedContact = contactService.saveContact(requestContact);
        ContactDto responseContactDto = contactMapper.contactToContactDto(storedContact);

        return ResponseEntity.created(URI.create(BASE_URL))
                                .body(responseContactDto);
    }

    @GetMapping("/contact")
    public ResponseEntity<ContactDto> getContactByEmail(@RequestParam("email") String email)
                                                                throws ContactNotFoundException {

        Contact contactByEmail = contactService.getContactByEmail(email);
        ContactDto responseContactDto = contactMapper.contactToContactDto(contactByEmail);

        return ResponseEntity.ok(responseContactDto);
    }

    @PutMapping("/contact")
    public ResponseEntity<ContactDto> updateContactByEmail(@RequestParam("email") String email,
                                                           @RequestBody SaveContactDto contactDto)
                                                                                    throws ContactNotFoundException {

        Contact requestUpdateContact = contactMapper.saveContactDtoToContact(contactDto);
        Contact updatedContact = contactService.updateContactByEmail(email, requestUpdateContact);
        ContactDto responseContactDto = contactMapper.contactToContactDto(updatedContact);

        return ResponseEntity.ok(responseContactDto);
    }

    @DeleteMapping("/contact")
    public ResponseEntity<Void> deleteContactByEmail(@RequestParam("email") String email) {

        contactService.deleteContactByEmail(email);

        return ResponseEntity.noContent()
                                .build();
    }

    @GetMapping
    public ResponseEntity<Page<ContactDto>> getAllContacts(@PageableDefault(size = 20, sort = "id")
                                                                                Pageable pageable) {
        Page<ContactDto> responseContacts = super.receiveContacts(pageable);

        return ResponseEntity.ok(responseContacts);
    }

    @GetMapping("/company")
    public ResponseEntity<Page<ContactDto>> getAllContactsByCompany(@PageableDefault(size = 20, sort = "id") Pageable pageable,
                                                                    @RequestParam("company") String company) {

        Page<ContactDto> responseContactsByCompany = super.receiveCompanyContacts(pageable, company);

        return ResponseEntity.ok(responseContactsByCompany);
    }

    @GetMapping("/job")
    public ResponseEntity<Page<ContactDto>> getAllContactsByJob(@PageableDefault(size = 20, sort = "id") Pageable pageable,
                                                                @RequestParam("jobName") String jobName) {

        Page<ContactDto> responseContactsByJobName = super.receiveJobContacts(pageable, jobName);

        return ResponseEntity.ok(responseContactsByJobName);
    }
}

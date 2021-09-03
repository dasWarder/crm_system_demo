package com.example.controller;

import com.example.ContactMapper;
import com.example.dto.ContactDto;
import com.example.exception.ContactNotFoundException;
import com.example.service.ContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage/contacts")
public class UserContactController extends AbstractContactController {


    public UserContactController(ContactMapper contactMapper, ContactService contactService) {
        super(contactMapper, contactService);
    }

    @GetMapping("/contact")
    public ResponseEntity<ContactDto> getContactByEmail(@RequestParam("email") String email)
                                                                                throws ContactNotFoundException {
        ContactDto contactDto = super.receiveContactByEmail(email);

        return ResponseEntity.ok(contactDto);
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

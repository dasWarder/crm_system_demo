package com.example.controller.contactManager;

import com.example.ContactMapper;
import com.example.dto.contact.ContactDto;
import com.example.exception.ContactNotFoundException;
import com.example.service.contact.ContactService;
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
    public ResponseEntity<Page<ContactDto>> getAllContacts(@RequestParam(value = "filteredBy", required = false) String filteredBy,
                                                           @RequestParam(value = "query", required = false) String query,
                                                           @PageableDefault(size = 20, sort = "id") Pageable pageable) {

        Page<ContactDto> responseContacts = Objects.isNull(filteredBy)?
                                                        super.receiveContacts(pageable) :
                                                        super.receiveCriteriaContact(filteredBy, query, pageable);
        return ResponseEntity.ok(responseContacts);
    }
}

package com.example.controller.contactManager.contactDetails;

import com.example.Contact;
import com.example.ContactMapper;
import com.example.controller.AbstractContactController;
import com.example.dto.ContactDetailsDto;
import com.example.exception.ContactNotFoundException;
import com.example.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( { "/manage/contacts", "/admin/manage/contacts" } )
public class ContactDetailsController extends AbstractContactController {

    public ContactDetailsController(ContactMapper contactMapper, ContactService contactService) {
        super(contactMapper, contactService);
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity<ContactDetailsDto> getContactDetailsById(@PathVariable("id") Long id)
                                                                                    throws ContactNotFoundException {
        Contact contactById = contactService.getContactById(id);
        ContactDetailsDto contactDetailsDto = contactMapper.contactToContactDetailsDto(contactById);

        return ResponseEntity.ok(contactDetailsDto);
    }
}

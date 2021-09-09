package com.example.web.controller.contactManager.contactDetails;

import com.example.exception.ContactNotFoundException;
import com.example.mapper.ContactMapper;
import com.example.mapper.dto.contact.ContactDetailsDto;
import com.example.model.contactManager.Contact;
import com.example.service.contact.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping( { "/manage/contacts", "/admin/manage/contacts" } )
public class ContactDetailsController {

    protected final ContactMapper contactMapper;

    protected final ContactService contactService;

    @GetMapping("/contact/{id}")
    public ResponseEntity<ContactDetailsDto> getContactDetailsById(@PathVariable("id") Long id)
                                                                                    throws ContactNotFoundException {
        Contact contactById = contactService.getContactById(id);
        ContactDetailsDto contactDetailsDto = contactMapper.contactToContactDetailsDto(contactById);

        return ResponseEntity.ok(contactDetailsDto);
    }
}

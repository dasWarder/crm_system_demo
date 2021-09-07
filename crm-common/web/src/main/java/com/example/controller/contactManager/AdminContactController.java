package com.example.controller.contactManager;

import com.example.contactManager.Contact;
import com.example.ContactMapper;
import com.example.dto.contact.ContactDto;
import com.example.dto.contact.SaveContactDto;
import com.example.exception.ContactNotFoundException;
import com.example.service.contact.ContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/admin/manage/contacts")
public class AdminContactController extends AbstractContactController {

    private static final String BASE_URL = "http://localhost:8080/admin/manage/contacts";

    public AdminContactController(ContactMapper contactMapper, ContactService contactService) {
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
    public ResponseEntity<Page<ContactDto>> getAllContacts(@RequestParam(value = "filteredBy", required = false) String filteredBy,
                                                           @RequestParam(value = "query", required = false) String query,
                                                           @PageableDefault(size = 20, sort = "id")
                                                                                Pageable pageable) {
        Page<ContactDto> responseContacts = Objects.isNull(filteredBy)?
                                                                    super.receiveContacts(pageable) :
                                                                    super.receiveCriteriaContact(filteredBy, query, pageable);
        return ResponseEntity.ok(responseContacts);
    }
}

package com.example.web.controller.contactManager.contactDetails;

import com.example.exception.ContactNotFoundException;
import com.example.mapper.contact.ContactMapper;
import com.example.mapper.dto.contact.ContactDetailsDto;
import com.example.model.contactManager.Contact;
import com.example.service.contact.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping({"/manage/contacts", "/admin/manage/contacts"})
public class ContactDetailsController {

  protected final ContactMapper contactMapper;

  protected final ContactService contactService;

  @GetMapping("/contact/{id}")
  public ResponseEntity<ContactDetailsDto> getContactDetailsById(
      @PathVariable("id")
          @Min(value = 1, message = "The id must be not less than 1")
          @NotNull(message = "The field is mandatory")
          Long id)
      throws ContactNotFoundException {
    Contact contactById = contactService.getContactById(id);
    ContactDetailsDto contactDetailsDto = contactMapper.contactToContactDetailsDto(contactById);

    return ResponseEntity.ok(contactDetailsDto);
  }
}

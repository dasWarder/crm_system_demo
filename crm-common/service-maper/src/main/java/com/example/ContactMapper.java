package com.example;

import com.example.contactManager.Contact;
import com.example.dto.contact.ContactDetailsDto;
import com.example.dto.contact.ContactDto;
import com.example.dto.contact.SaveContactDto;
import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper {

    ContactDto contactToContactDto(Contact contact);

    ContactDetailsDto contactToContactDetailsDto(Contact contact);

    SaveContactDto contactToSaveContactDto(Contact contact);
}

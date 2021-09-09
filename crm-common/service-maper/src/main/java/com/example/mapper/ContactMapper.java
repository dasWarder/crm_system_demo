package com.example.mapper;

import com.example.model.contactManager.Contact;
import com.example.mapper.dto.contact.ContactDetailsDto;
import com.example.mapper.dto.contact.ContactDto;
import com.example.mapper.dto.contact.SaveContactDto;
import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper {

    ContactDto contactToContactDto(Contact contact);

    ContactDetailsDto contactToContactDetailsDto(Contact contact);

    SaveContactDto contactToSaveContactDto(Contact contact);
}

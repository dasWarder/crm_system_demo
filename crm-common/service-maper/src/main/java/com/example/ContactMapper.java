package com.example;

import com.example.dto.contact.ContactDetailsDto;
import com.example.dto.contact.ContactDto;
import com.example.dto.contact.SaveContactDto;
import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper {

    Contact saveContactDtoToContact(SaveContactDto dto);

    ContactDto contactToContactDto(Contact contact);

    Contact contactDtoToContact(ContactDto dto);

    ContactDetailsDto contactToContactDetailsDto(Contact contact);

    SaveContactDto contactToSaveContactDto(Contact contact);
}

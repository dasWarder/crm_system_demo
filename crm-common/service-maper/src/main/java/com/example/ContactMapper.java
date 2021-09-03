package com.example;

import com.example.dto.ContactDto;
import com.example.dto.SaveContactDto;
import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper {

    Contact saveContactDtoToContact(SaveContactDto dto);

    ContactDto contactToContactDto(Contact contact);

    Contact contactDtoToContact(ContactDto dto);
}

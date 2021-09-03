package com.example;

import com.example.dto.ContactDto;
import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper {

    ContactDto contactToContactDto(Contact contact);

    Contact contactDtoToContact(ContactDto dto);
}

package com.example.mapper;

import com.example.mapper.dto.contact.ContactDetailsDto;
import com.example.mapper.dto.contact.ContactDto;
import com.example.mapper.dto.contact.SaveContactDto;
import com.example.mapper.dto.user.SaveUserDto;
import com.example.model.contactManager.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ContactMapper {

  ContactDto contactToContactDto(Contact contact);

  ContactDetailsDto contactToContactDetailsDto(Contact contact);

  @Mapping(target = "email", ignore = true)
  Contact saveContactDtoToContact(SaveContactDto contactDto);


  Contact saveUserDtoToContact(SaveUserDto dto);
}

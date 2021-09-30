package com.example.mapper;

import com.example.mapper.dto.user.BaseUserDto;
import com.example.mapper.dto.user.DetailsUserDto;
import com.example.mapper.dto.user.SaveUserDto;
import com.example.mapper.dto.user.admin.AdminDetailsUserDto;
import com.example.model.contactManager.Contact;
import com.example.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ContactMapper.class, AuthorityMapper.class})
public interface UserMapper {

  @Mapping(target = "email", source = "email")
  BaseUserDto userToBaseUserDto(User user);

  @Mapping(target = "email", source = "email")
  @Mapping(target = "enabled", source = "enabled")
  @Mapping(target = "registrationDate", source = "registrationDate")
  DetailsUserDto userToDetailsUserDto(User user);

  @Mapping(target = "firstName", source = "contact.firstName")
  @Mapping(target = "lastName", source = "contact.lastName")
  @Mapping(target = "jobTitle", source = "contact.jobTitle")
  @Mapping(target = "company", source = "contact.company")
  @Mapping(target = "email", source = "user.email")
  @Mapping(target = "country", source = "contact.country")
  @Mapping(target = "mobilePhone", source = "contact.mobilePhone")
  @Mapping(target = "password", source = "user.password")
  SaveUserDto userToSaveUserDto(Contact contact, User user);

  @Mapping(target = "firstName", source = "contact.firstName")
  @Mapping(target = "lastName", source = "contact.lastName")
  @Mapping(target = "jobTitle", source = "contact.jobTitle")
  @Mapping(target = "company", source = "contact.company")
  @Mapping(target = "email", source = "user.email")
  @Mapping(target = "country", source = "contact.country")
  @Mapping(target = "mobilePhone", source = "contact.mobilePhone")
  @Mapping(target = "enabled", source = "user.enabled")
  @Mapping(target = "registrationDate", source = "user.registrationDate")
  @Mapping(target = "role", source = "user.role.authority")
  AdminDetailsUserDto userToAdminDetailsUserDto(Contact contact, User user);
}

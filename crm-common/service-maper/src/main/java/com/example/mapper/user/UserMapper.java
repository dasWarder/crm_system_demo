package com.example.mapper.user;

import com.example.mapper.authority.AuthorityMapper;
import com.example.mapper.contact.ContactMapper;
import com.example.mapper.dto.user.BaseUserDto;
import com.example.mapper.dto.user.DetailsUserDto;
import com.example.mapper.dto.user.SaveUserDto;
import com.example.mapper.dto.user.admin.AdminDetailsUserDto;
import com.example.mapper.dto.user.admin.CreateUserDto;
import com.example.mapper.dto.user.superadmin.SuperAdminUserDetailsDto;
import com.example.model.contactManager.Contact;
import com.example.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { ContactMapper.class, AuthorityMapper.class })
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
  @Mapping(target = "role", source = "user.role.authority")
  @Mapping(target = "country", source = "contact.country")
  @Mapping(target = "mobilePhone", source = "contact.mobilePhone")
  CreateUserDto userToCreateUserDto(Contact contact, User user);

  @Mapping(target = "firstName", source = "user.contact.firstName")
  @Mapping(target = "lastName", source = "user.contact.lastName")
  @Mapping(target = "jobTitle", source = "user.contact.jobTitle")
  @Mapping(target = "company", source = "user.contact.company")
  @Mapping(target = "country", source = "user.contact.country")
  @Mapping(target = "mobilePhone", source = "user.contact.mobilePhone")
  @Mapping(target = "enabled", source = "user.enabled")
  @Mapping(target = "role", source = "user.role.authority")
  AdminDetailsUserDto userToAdminDetailsUserDto(User user);


  @Mapping(target = "firstName", source = "user.contact.firstName")
  @Mapping(target = "lastName", source = "user.contact.lastName")
  @Mapping(target = "jobTitle", source = "user.contact.jobTitle")
  @Mapping(target = "company", source = "user.contact.company")
  @Mapping(target = "country", source = "user.contact.country")
  @Mapping(target = "mobilePhone", source = "user.contact.mobilePhone")
  SuperAdminUserDetailsDto userToSuperAdminUserDetailsDto(User user);
}

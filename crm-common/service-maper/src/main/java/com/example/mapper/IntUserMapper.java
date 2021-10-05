package com.example.mapper;

import com.example.mapper.dto.user.SaveUserDto;
import com.example.model.contactManager.Contact;
import com.example.model.user.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;

@Mapper(uses = { PasswordEncoderMapper.class }, imports = LocalDate.class)
public interface IntUserMapper {

  @Mapping(target = "role", ignore = true)
  @Mapping(target = "email", source = "dto.email")
  @Mapping(target = "enabled", constant = "true")
  @Mapping(target = "password", source = "dto.password", qualifiedBy = EncodedMapping.class)
  User saveUserDtoToUser(Contact contact, SaveUserDto dto);

  @AfterMapping
  default void map(@MappingTarget User.UserBuilder target) {
    target.registrationDate(LocalDate.now());
  }
}

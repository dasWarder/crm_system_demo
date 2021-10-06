package com.example.mapper;

import com.example.mapper.dto.user.SaveUserDto;
import com.example.mapper.dto.user.admin.CreateUserDto;
import com.example.model.user.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;

@Mapper(uses = { PasswordEncoderMapper.class }, imports = LocalDate.class)
public interface CreateUserMapper {

  @Mapping(target = "role", ignore = true)
  @Mapping(target = "enabled", constant = "true")
  @Mapping(target = "password", source = "password", qualifiedBy = EncodedMapping.class)
  User saveUserDtoToUser(SaveUserDto dto);

  @Mapping(target = "role", ignore = true)
  @Mapping(target = "password", ignore = true)
  User createUserDtoToDefaultUser(CreateUserDto dto);

  @AfterMapping
  default void map(@MappingTarget User.UserBuilder target) {
    target.registrationDate(LocalDate.now());
  }
}

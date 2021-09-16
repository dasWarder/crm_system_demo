package com.example.mapper;

import com.example.mapper.dto.user.BaseUserDto;
import com.example.mapper.dto.user.DetailsUserDto;
import com.example.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

  @Mapping(target = "email", source = "email")
  BaseUserDto userToBaseUserDto(User user);


  @Mapping(target = "email", source = "email")
  @Mapping(target = "enabled", source = "enabled")
  @Mapping(target = "registrationDate", source = "registrationDate")
  DetailsUserDto userToDetailsUserDto(User user);
}

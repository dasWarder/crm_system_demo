package com.example.mapper;

import com.example.mapper.dto.user.BaseUserDto;
import com.example.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

  @Mapping(target = "email", source = "email")
  BaseUserDto userToBaseUserDto(User user);
}

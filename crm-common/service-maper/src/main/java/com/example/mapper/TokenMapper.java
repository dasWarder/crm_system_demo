package com.example.mapper;

import com.example.mapper.dto.user.token.TokenDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TokenMapper {

  @Mapping(target = "authToken", source = "authToken")
  @Mapping(target = "refreshToken", source = "refreshToken")
  TokenDto fromStringsToToken(String authToken, String refreshToken);
}

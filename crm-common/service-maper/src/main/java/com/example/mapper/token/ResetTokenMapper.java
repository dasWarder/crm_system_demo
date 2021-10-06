package com.example.mapper.token;


import com.example.mapper.dto.user.token.ResetTokenDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ResetTokenMapper {

    @Mapping(target = "resetToken", source = "resetToken")
    ResetTokenDto stringToResetTokenDto(String resetToken);
}

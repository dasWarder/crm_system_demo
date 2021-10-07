package com.example.mapper.authority;

import com.example.mapper.dto.authority.AuthorityDetailsDto;
import com.example.mapper.dto.authority.AuthorityDto;
import com.example.model.user.UserAuthority;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AuthorityMapper {

    String roleNameToRole(UserAuthority authority);

    UserAuthority authorityDtoToUserAuthority(AuthorityDto dto);

    AuthorityDto userAuthorityToAuthorityDto(UserAuthority authority);

    @Mapping(target = "users", source = "count")
    AuthorityDetailsDto userAuthorityToAuthorityDetailsDto(Long count, UserAuthority authority);
}

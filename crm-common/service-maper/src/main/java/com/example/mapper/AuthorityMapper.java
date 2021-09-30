package com.example.mapper;

import com.example.model.user.UserAuthority;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AuthorityMapper {

    String roleNameToRole(UserAuthority authority);
}

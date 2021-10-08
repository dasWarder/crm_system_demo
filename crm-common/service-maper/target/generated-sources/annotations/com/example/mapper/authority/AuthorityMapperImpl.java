package com.example.mapper.authority;

import com.example.mapper.dto.authority.AuthorityDetailsDto;
import com.example.mapper.dto.authority.AuthorityDto;
import com.example.model.user.UserAuthority;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-08T17:20:23+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class AuthorityMapperImpl implements AuthorityMapper {

    @Override
    public String roleNameToRole(UserAuthority authority) {
        if ( authority == null ) {
            return null;
        }

        String string = new String();

        return string;
    }

    @Override
    public UserAuthority authorityDtoToUserAuthority(AuthorityDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserAuthority userAuthority = new UserAuthority();

        userAuthority.setAuthority( dto.getAuthority() );

        return userAuthority;
    }

    @Override
    public AuthorityDto userAuthorityToAuthorityDto(UserAuthority authority) {
        if ( authority == null ) {
            return null;
        }

        AuthorityDto authorityDto = new AuthorityDto();

        authorityDto.setAuthority( authority.getAuthority() );

        return authorityDto;
    }

    @Override
    public AuthorityDetailsDto userAuthorityToAuthorityDetailsDto(Long count, UserAuthority authority) {
        if ( count == null && authority == null ) {
            return null;
        }

        AuthorityDetailsDto authorityDetailsDto = new AuthorityDetailsDto();

        if ( count != null ) {
            authorityDetailsDto.setUsers( count );
        }
        if ( authority != null ) {
            authorityDetailsDto.setAuthority( authority.getAuthority() );
        }

        return authorityDetailsDto;
    }
}

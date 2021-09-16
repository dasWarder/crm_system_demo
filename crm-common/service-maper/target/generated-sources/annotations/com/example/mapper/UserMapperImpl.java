package com.example.mapper;

import com.example.mapper.dto.user.BaseUserDto;
import com.example.mapper.dto.user.DetailsUserDto;
import com.example.model.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-16T09:40:32+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public BaseUserDto userToBaseUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        BaseUserDto baseUserDto = new BaseUserDto();

        baseUserDto.setEmail( user.getEmail() );

        return baseUserDto;
    }

    @Override
    public DetailsUserDto userToDetailsUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        DetailsUserDto detailsUserDto = new DetailsUserDto();

        detailsUserDto.setEmail( user.getEmail() );
        detailsUserDto.setEnabled( user.isEnabled() );
        detailsUserDto.setRegistrationDate( user.getRegistrationDate() );

        return detailsUserDto;
    }
}

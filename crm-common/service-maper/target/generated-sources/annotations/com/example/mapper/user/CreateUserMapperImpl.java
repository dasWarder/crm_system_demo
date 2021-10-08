package com.example.mapper.user;

import com.example.mapper.dto.user.SaveUserDto;
import com.example.mapper.dto.user.admin.CreateUserDto;
import com.example.mapper.dto.user.superadmin.CreateFullUserDto;
import com.example.model.user.User;
import com.example.model.user.User.UserBuilder;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-08T17:20:23+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class CreateUserMapperImpl implements CreateUserMapper {

    @Autowired
    private PasswordEncoderMapper passwordEncoderMapper;

    @Override
    public User saveUserDtoToUser(SaveUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.password( passwordEncoderMapper.encode( dto.getPassword() ) );
        user.email( dto.getEmail() );

        user.enabled( true );

        map( user );

        return user.build();
    }

    @Override
    public User createUserDtoToDefaultUser(CreateUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.email( dto.getEmail() );
        user.enabled( dto.isEnabled() );

        map( user );

        return user.build();
    }

    @Override
    public User createFullUserDtoToUser(CreateFullUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.password( passwordEncoderMapper.encode( dto.getPassword() ) );
        user.email( dto.getEmail() );
        user.enabled( dto.isEnabled() );
        user.registrationDate( dto.getRegistrationDate() );

        map( user );

        return user.build();
    }
}

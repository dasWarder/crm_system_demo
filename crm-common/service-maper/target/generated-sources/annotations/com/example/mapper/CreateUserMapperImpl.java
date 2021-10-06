package com.example.mapper;

import com.example.mapper.dto.user.SaveUserDto;
import com.example.mapper.dto.user.admin.CreateUserDto;
import com.example.model.user.User;
import com.example.model.user.User.UserBuilder;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-06T14:23:44+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (AdoptOpenJDK)"
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
}

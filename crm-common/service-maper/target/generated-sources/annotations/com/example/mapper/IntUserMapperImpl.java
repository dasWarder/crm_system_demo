package com.example.mapper;

import com.example.mapper.dto.user.SaveUserDto;
import com.example.model.contactManager.Contact;
import com.example.model.user.User;
import com.example.model.user.User.UserBuilder;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-05T17:00:36+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (AdoptOpenJDK)"
)
@Component
public class IntUserMapperImpl implements IntUserMapper {

    @Autowired
    private PasswordEncoderMapper passwordEncoderMapper;

    @Override
    public User saveUserDtoToUser(Contact contact, SaveUserDto dto) {
        if ( contact == null && dto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        if ( contact != null ) {
            user.id( contact.getId() );
        }
        if ( dto != null ) {
            user.email( dto.getEmail() );
            user.password( passwordEncoderMapper.encode( dto.getPassword() ) );
        }
        user.enabled( true );

        map( user );

        return user.build();
    }
}

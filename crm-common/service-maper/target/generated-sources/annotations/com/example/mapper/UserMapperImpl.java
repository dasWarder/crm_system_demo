package com.example.mapper;

import com.example.mapper.dto.user.BaseUserDto;
import com.example.mapper.dto.user.DetailsUserDto;
import com.example.mapper.dto.user.SaveUserDto;
import com.example.model.contactManager.Contact;
import com.example.model.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-29T14:56:39+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (AdoptOpenJDK)"
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

    @Override
    public SaveUserDto userToSaveUserDto(Contact contact, User user) {
        if ( contact == null && user == null ) {
            return null;
        }

        SaveUserDto saveUserDto = new SaveUserDto();

        if ( contact != null ) {
            saveUserDto.setFirstName( contact.getFirstName() );
            saveUserDto.setLastName( contact.getLastName() );
            saveUserDto.setJobTitle( contact.getJobTitle() );
            saveUserDto.setCompany( contact.getCompany() );
            saveUserDto.setCountry( contact.getCountry() );
            saveUserDto.setMobilePhone( contact.getMobilePhone() );
        }
        if ( user != null ) {
            saveUserDto.setEmail( user.getEmail() );
            saveUserDto.setPassword( user.getPassword() );
        }

        return saveUserDto;
    }
}

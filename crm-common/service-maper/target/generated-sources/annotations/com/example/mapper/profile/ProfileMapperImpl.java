package com.example.mapper.profile;

import com.example.mapper.dto.profile.ProfileDto;
import com.example.model.contactManager.Contact;
import com.example.model.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-06T15:13:14+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public ProfileDto toProfileDto(User currentUser, Contact contact) {
        if ( currentUser == null && contact == null ) {
            return null;
        }

        ProfileDto profileDto = new ProfileDto();

        if ( currentUser != null ) {
            profileDto.setEnabled( currentUser.isEnabled() );
            profileDto.setRegistrationDate( currentUser.getRegistrationDate() );
        }
        if ( contact != null ) {
            profileDto.setFirstName( contact.getFirstName() );
            profileDto.setLastName( contact.getLastName() );
            profileDto.setEmail( contact.getEmail() );
            profileDto.setCompany( contact.getCompany() );
            profileDto.setJobTitle( contact.getJobTitle() );
            profileDto.setCountry( contact.getCountry() );
            profileDto.setPhoneNumber( contact.getMobilePhone() );
        }

        return profileDto;
    }
}

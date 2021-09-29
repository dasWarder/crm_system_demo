package com.example.mapper;

import com.example.mapper.dto.profile.ProfileDto;
import com.example.model.contactManager.Contact;
import com.example.model.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-29T16:49:41+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (AdoptOpenJDK)"
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

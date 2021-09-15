package com.example.mapper;

import com.example.mapper.dto.contact.ContactDetailsDto;
import com.example.mapper.dto.contact.ContactDto;
import com.example.mapper.dto.contact.SaveContactDto;
import com.example.model.contactManager.Contact;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-15T13:09:05+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (AdoptOpenJDK)"
)
@Component
public class ContactMapperImpl implements ContactMapper {

    @Override
    public ContactDto contactToContactDto(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        ContactDto contactDto = new ContactDto();

        contactDto.setId( contact.getId() );
        contactDto.setFirstName( contact.getFirstName() );
        contactDto.setLastName( contact.getLastName() );
        contactDto.setJobTitle( contact.getJobTitle() );
        contactDto.setCompany( contact.getCompany() );
        contactDto.setEmail( contact.getEmail() );

        return contactDto;
    }

    @Override
    public ContactDetailsDto contactToContactDetailsDto(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        ContactDetailsDto contactDetailsDto = new ContactDetailsDto();

        contactDetailsDto.setFirstName( contact.getFirstName() );
        contactDetailsDto.setLastName( contact.getLastName() );
        contactDetailsDto.setJobTitle( contact.getJobTitle() );
        contactDetailsDto.setCompany( contact.getCompany() );
        contactDetailsDto.setCountry( contact.getCountry() );
        contactDetailsDto.setEmail( contact.getEmail() );
        contactDetailsDto.setMobilePhone( contact.getMobilePhone() );

        return contactDetailsDto;
    }

    @Override
    public SaveContactDto contactToSaveContactDto(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        SaveContactDto saveContactDto = new SaveContactDto();

        saveContactDto.setId( contact.getId() );
        saveContactDto.setFirstName( contact.getFirstName() );
        saveContactDto.setLastName( contact.getLastName() );
        saveContactDto.setJobTitle( contact.getJobTitle() );
        saveContactDto.setCompany( contact.getCompany() );
        saveContactDto.setEmail( contact.getEmail() );
        saveContactDto.setCountry( contact.getCountry() );
        saveContactDto.setMobilePhone( contact.getMobilePhone() );

        return saveContactDto;
    }
}

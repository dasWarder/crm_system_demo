package com.example;

import com.example.dto.ContactDetailsDto;
import com.example.dto.ContactDto;
import com.example.dto.SaveContactDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-03T15:08:58+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class ContactMapperImpl implements ContactMapper {

    @Override
    public Contact saveContactDtoToContact(SaveContactDto dto) {
        if ( dto == null ) {
            return null;
        }

        Contact contact = new Contact();

        contact.setFirstName( dto.getFirstName() );
        contact.setLastName( dto.getLastName() );
        contact.setJobTitle( dto.getJobTitle() );
        contact.setCompany( dto.getCompany() );
        contact.setCountry( dto.getCountry() );
        contact.setEmail( dto.getEmail() );
        contact.setMobilePhone( dto.getMobilePhone() );

        return contact;
    }

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
    public Contact contactDtoToContact(ContactDto dto) {
        if ( dto == null ) {
            return null;
        }

        Contact contact = new Contact();

        contact.setId( dto.getId() );
        contact.setFirstName( dto.getFirstName() );
        contact.setLastName( dto.getLastName() );
        contact.setJobTitle( dto.getJobTitle() );
        contact.setCompany( dto.getCompany() );
        contact.setEmail( dto.getEmail() );

        return contact;
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
}

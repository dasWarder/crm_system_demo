package com.example;

import com.example.Contact.ContactBuilder;
import com.example.dto.ContactDetailsDto;
import com.example.dto.ContactDto;
import com.example.dto.SaveContactDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-06T11:23:09+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class ContactMapperImpl implements ContactMapper {

    @Override
    public Contact saveContactDtoToContact(SaveContactDto dto) {
        if ( dto == null ) {
            return null;
        }

        ContactBuilder contact = Contact.builder();

        contact.id( dto.getId() );
        contact.firstName( dto.getFirstName() );
        contact.lastName( dto.getLastName() );
        contact.jobTitle( dto.getJobTitle() );
        contact.company( dto.getCompany() );
        contact.country( dto.getCountry() );
        contact.email( dto.getEmail() );
        contact.mobilePhone( dto.getMobilePhone() );

        return contact.build();
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

        ContactBuilder contact = Contact.builder();

        contact.id( dto.getId() );
        contact.firstName( dto.getFirstName() );
        contact.lastName( dto.getLastName() );
        contact.jobTitle( dto.getJobTitle() );
        contact.company( dto.getCompany() );
        contact.email( dto.getEmail() );

        return contact.build();
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

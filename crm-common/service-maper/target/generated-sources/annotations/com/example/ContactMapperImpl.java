package com.example;

import com.example.contactManager.Contact;
import com.example.contactManager.Contact.ContactBuilder;
import com.example.dto.contact.ContactDetailsDto;
import com.example.dto.contact.ContactDto;
import com.example.dto.contact.SaveContactDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-07T17:12:22+0300",
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

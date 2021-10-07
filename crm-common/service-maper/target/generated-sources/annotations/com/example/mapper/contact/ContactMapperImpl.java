package com.example.mapper.contact;

import com.example.mapper.dto.contact.ContactDetailsDto;
import com.example.mapper.dto.contact.ContactDto;
import com.example.mapper.dto.contact.SaveContactDto;
import com.example.mapper.dto.user.SaveUserDto;
import com.example.mapper.dto.user.admin.CreateUserDto;
import com.example.mapper.dto.user.superadmin.CreateFullUserDto;
import com.example.model.contactManager.Contact;
import com.example.model.contactManager.Contact.ContactBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-07T10:29:14+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
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
    public Contact saveContactDtoToContact(SaveContactDto contactDto) {
        if ( contactDto == null ) {
            return null;
        }

        ContactBuilder contact = Contact.builder();

        contact.id( contactDto.getId() );
        contact.firstName( contactDto.getFirstName() );
        contact.lastName( contactDto.getLastName() );
        contact.jobTitle( contactDto.getJobTitle() );
        contact.company( contactDto.getCompany() );
        contact.country( contactDto.getCountry() );
        contact.mobilePhone( contactDto.getMobilePhone() );

        return contact.build();
    }

    @Override
    public Contact saveUserDtoToContact(SaveUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        ContactBuilder contact = Contact.builder();

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
    public Contact createUserDtoToDefaultContact(CreateUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        ContactBuilder contact = Contact.builder();

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
    public Contact createFullUserDtoToContact(CreateFullUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        ContactBuilder contact = Contact.builder();

        contact.firstName( dto.getFirstName() );
        contact.lastName( dto.getLastName() );
        contact.jobTitle( dto.getJobTitle() );
        contact.company( dto.getCompany() );
        contact.country( dto.getCountry() );
        contact.email( dto.getEmail() );
        contact.mobilePhone( dto.getMobilePhone() );

        return contact.build();
    }
}

package com.example;

import com.example.dto.ContactDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-03T09:13:53+0300",
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

        contactDto.setFirstName( contact.getFirstName() );
        contactDto.setLastName( contact.getLastName() );
        contactDto.setJobTitle( contact.getJobTitle() );
        contactDto.setCompany( contact.getCompany() );
        contactDto.setCountry( contact.getCountry() );
        contactDto.setEmail( contact.getEmail() );
        contactDto.setMobilePhone( contact.getMobilePhone() );

        return contactDto;
    }

    @Override
    public Contact contactDtoToContact(ContactDto dto) {
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
}

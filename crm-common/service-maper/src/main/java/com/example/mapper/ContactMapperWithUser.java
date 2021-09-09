package com.example.mapper;

import com.example.model.contactManager.Contact;
import com.example.mapper.dto.contact.ContactDetailsDto;
import com.example.mapper.dto.contact.ContactDto;
import com.example.mapper.dto.contact.SaveContactDto;
import com.example.exception.UserNotFoundException;
import com.example.service.user.UserService;
import com.example.model.user.User;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class ContactMapperWithUser {

    @Autowired
    private UserService userService;

    public Contact saveContactDtoToDto(SaveContactDto dto) throws UserNotFoundException {

        User validUser = userService.getUserByEmail("alex@gmail.com");

        Contact contact = Contact.builder()
                                        .id(dto.getId())
                                        .firstName(dto.getFirstName())
                                        .lastName(dto.getLastName())
                                        .company(dto.getCompany())
                                        .jobTitle(dto.getJobTitle())
                                        .email(dto.getEmail())
                                        .country(dto.getCountry())
                                        .mobilePhone(dto.getMobilePhone())
                                        .user(validUser)
                                        .build();
        return contact;
    }

    public Contact contactDtoToContact(ContactDto dto) throws UserNotFoundException {

        User validUser = userService.getUserByEmail("alex@gmail.com");

        Contact contact = Contact.builder()
                                    .id(dto.getId())
                                    .firstName(dto.getFirstName())
                                    .lastName(dto.getLastName())
                                    .company(dto.getCompany())
                                    .jobTitle(dto.getJobTitle())
                                    .email(dto.getEmail())
                                    .user(validUser)
                                    .build();
        return contact;
    }

    public Contact contactDetailsDtoToContact(ContactDetailsDto dto) throws UserNotFoundException {

        User validUser = userService.getUserByEmail("alex@gmail.com");

        Contact contact = Contact.builder()
                                    .firstName(dto.getFirstName())
                                    .lastName(dto.getLastName())
                                    .company(dto.getCompany())
                                    .jobTitle(dto.getJobTitle())
                                    .email(dto.getEmail())
                                    .user(validUser)
                                    .build();

        return contact;
    }

}

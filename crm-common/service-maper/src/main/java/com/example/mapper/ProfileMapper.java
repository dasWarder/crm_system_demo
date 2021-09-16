package com.example.mapper;

import com.example.mapper.dto.contact.ContactDetailsDto;
import com.example.mapper.dto.profile.ProfileDto;
import com.example.mapper.dto.user.DetailsUserDto;
import com.example.model.contactManager.Contact;
import com.example.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { UserMapper.class, ContactMapper.class })
public interface ProfileMapper {

    @Mapping(target = "firstName", source = "contact.firstName")
    @Mapping(target = "lastName", source = "contact.lastName")
    @Mapping(target = "email", source = "contact.email")
    @Mapping(target = "company", source = "contact.company")
    @Mapping(target = "jobTitle", source = "contact.jobTitle")
    @Mapping(target = "country", source = "contact.country")
    @Mapping(target = "phoneNumber", source = "contact.mobilePhone")
    @Mapping(target = "enabled", source = "currentUser.enabled")
    @Mapping(target = "registrationDate", source = "currentUser.registrationDate")
    ProfileDto toProfileDto(User currentUser, Contact contact);
}

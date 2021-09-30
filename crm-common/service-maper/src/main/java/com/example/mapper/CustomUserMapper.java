package com.example.mapper;

import com.example.mapper.dto.user.SaveUserDto;
import com.example.mapper.dto.user.UpdateUserPasswordDto;
import com.example.mapper.dto.user.admin.CreateUserDto;
import com.example.model.contactManager.Contact;
import com.example.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Slf4j
@Mapper
public abstract class CustomUserMapper {

  @Autowired private PasswordEncoder passwordEncoder;

  public User saveUserDtoToUser(SaveUserDto dto) {

    log.info("Try to map a save user DTO to the user");

    User user =
        User.builder()
            .email(dto.getEmail())
            .password(passwordEncoder.encode(dto.getPassword()))
            .enabled(true)
            .registrationDate(LocalDate.now())
            .build();

    Contact contact = populateContactCard(dto, user);

    user.setContact(contact);

    return user;
  }

  public User createUserDtoToDefaultUser(CreateUserDto dto) {

    log.info("Map a CreateUserDto to user");

    User user = User.builder().email(dto.getEmail()).enabled(dto.isEnabled()).build();
    Contact contact = populateContactCard(dto, user);
    user.setContact(contact);

    return user;
  }

  private <T extends CreateUserDto> Contact populateContactCard(T dto, User user) {

    Contact contact =
        Contact.builder()
            .firstName(dto.getFirstName())
            .lastName(dto.getLastName())
            .jobTitle(dto.getJobTitle())
            .company(dto.getCompany())
            .country(dto.getCountry())
            .email(dto.getEmail())
            .mobilePhone(dto.getMobilePhone())
            .user(user)
            .build();

    return contact;
  }
}

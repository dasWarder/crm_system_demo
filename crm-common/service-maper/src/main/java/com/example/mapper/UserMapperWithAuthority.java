package com.example.mapper;

import com.example.model.contactManager.Contact;
import com.example.mapper.dto.user.SaveUserDto;
import com.example.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;


@Slf4j
@Mapper
public abstract class UserMapperWithAuthority {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUserDtoToUser(SaveUserDto dto) {

      log.info("Try to map a save user DTO to the user");

      User user = User.builder()
                      .email(dto.getEmail())
                      .password(passwordEncoder.encode(dto.getPassword()))
                      .enabled(true)
                      .registrationDate(LocalDate.now())
                      .build();

      Contact userContactCard = Contact.builder()
                                            .firstName(dto.getFirstName())
                                            .lastName(dto.getLastName())
                                            .jobTitle(dto.getJobTitle())
                                            .company(dto.getCompany())
                                            .country(dto.getCountry())
                                            .email(dto.getEmail())
                                            .mobilePhone(dto.getMobilePhone())
                                            .user(user)
                                            .build();
        user.setContact(userContactCard);

        return user;
    }
}

package com.example.web.controller.profile;

import com.example.exception.ContactNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.exception.WrongPasswordException;
import com.example.mapper.ContactMapper;
import com.example.mapper.ProfileMapper;
import com.example.mapper.UserMapper;
import com.example.mapper.dto.contact.ContactDetailsDto;
import com.example.mapper.dto.contact.SaveContactDto;
import com.example.mapper.dto.profile.ProfileDto;
import com.example.mapper.dto.user.BaseUserDto;
import com.example.mapper.dto.user.UpdateUserEmailDto;
import com.example.mapper.dto.user.UpdateUserPasswordDto;
import com.example.model.contactManager.Contact;
import com.example.model.user.User;
import com.example.service.contact.ContactService;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manage/profile")
public class UserProfileController {

  private final UserMapper userMapper;

  private final UserService userService;

  private final ContactMapper contactMapper;

  private final ProfileMapper profileMapper;

  private final ContactService contactService;

  @GetMapping
  public ResponseEntity<ProfileDto> getUserProfile() throws UserNotFoundException {

    User currentUser = userService.getCurrentUser();
    Contact userContact = currentUser.getContact();

    ProfileDto profileDto = profileMapper.toProfileDto(currentUser, userContact);

    return ResponseEntity.ok(profileDto);
  }

  @PutMapping("/contact")
  public ResponseEntity<ContactDetailsDto> updateContactInformation(@RequestBody SaveContactDto dto)
      throws UserNotFoundException, ContactNotFoundException {

    Contact updateContact = contactMapper.saveContactDtoToContact(dto);
    User currentUser = userService.getCurrentUser();
    Contact currentContact = currentUser.getContact();

    Contact storedContact =
        contactService.updateContactByEmail(currentContact.getEmail(), updateContact);
    ContactDetailsDto responseContactDto = contactMapper.contactToContactDetailsDto(storedContact);

    return ResponseEntity.ok(responseContactDto);
  }

  @PutMapping("/main/password")
  public ResponseEntity<BaseUserDto> updateUserPassword(
      @RequestParam("email") String email, @RequestBody UpdateUserPasswordDto dto)
      throws UserNotFoundException, WrongPasswordException {

    User updatedUser =
        userService.updateUserPassByEmail(email, dto.getOldPassword(), dto.getPassword());
    BaseUserDto responseDto = userMapper.userToBaseUserDto(updatedUser);

    return ResponseEntity.ok(responseDto);
  }

  @PutMapping("/main/email")
  public ResponseEntity<BaseUserDto> updateUserEmail(@RequestBody UpdateUserEmailDto dto)
      throws UserNotFoundException {

    User updatedUserEmail = userService.updateUserEmail(dto.getOldEmail(), dto.getEmail());
    BaseUserDto responseDto = userMapper.userToBaseUserDto(updatedUserEmail);

    return ResponseEntity.ok(responseDto);
  }
}

package com.example.web.controller.users;

import com.example.exception.AuthorityNotFoundException;
import com.example.exception.UserAlreadyExistException;
import com.example.exception.UserNotFoundException;
import com.example.mapper.contact.ContactMapper;
import com.example.mapper.dto.user.BaseUserDto;
import com.example.mapper.dto.user.superadmin.CreateFullUserDto;
import com.example.mapper.dto.user.superadmin.SuperAdminUserDetailsDto;
import com.example.mapper.user.CreateUserMapper;
import com.example.mapper.user.UserMapper;
import com.example.model.contactManager.Contact;
import com.example.model.user.User;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/manage/leader/users")
public class SuperAdminUsersController {

  @Value("${url.base}")
  private String baseUrl;

  private final UserMapper userMapper;

  private final UserService userService;

  private final ContactMapper contactMapper;

  private final CreateUserMapper createUserMapper;

  @PostMapping("/user")
  public ResponseEntity<BaseUserDto> saveUser(@RequestBody @Valid CreateFullUserDto dto)
      throws UserAlreadyExistException, AuthorityNotFoundException {

    User userToStore = createUserMapper.createFullUserDtoToUser(dto);
    Contact contact = contactMapper.createFullUserDtoToContact(dto);
    User storedUser = userService.saveFullUser(contact, dto.getRole(), userToStore);
    BaseUserDto responseDto = userMapper.userToBaseUserDto(storedUser);

    return ResponseEntity.created(URI.create(baseUrl + "/manage/leader/users")).body(responseDto);
  }

  @GetMapping("/user")
  public ResponseEntity<SuperAdminUserDetailsDto> getUserDetails(@RequestParam("email") String email) throws UserNotFoundException {

    User userByEmail = userService.getUserByEmail(email);
    SuperAdminUserDetailsDto responseDto = userMapper.userToSuperAdminUserDetailsDto(userByEmail);

    return ResponseEntity.ok(responseDto);
  }
}

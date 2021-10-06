package com.example.web.controller.users;

import com.example.exception.*;
import com.example.mapper.ContactMapper;
import com.example.mapper.CreateUserMapper;
import com.example.mapper.UserMapper;
import com.example.mapper.dto.user.BaseUserDto;
import com.example.mapper.dto.user.admin.AdminDetailsUserDto;
import com.example.mapper.dto.user.admin.CreateUserDto;
import com.example.model.contactManager.Contact;
import com.example.model.user.User;
import com.example.service.contact.ContactService;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manage/admin/users")
public class AdminUsersController {

  @Value("${url.base}")
  private String baseUrl;

  private final UserMapper userMapper;

  private final UserService userService;

  private final ContactMapper contactMapper;

  private final ContactService contactService;

  private final CreateUserMapper customUserMapper;

  @PostMapping("/user")
  public ResponseEntity<BaseUserDto> createUser(@RequestBody CreateUserDto dto)
      throws UserAlreadyExistException, AuthorityNotFoundException {

    User defaultUser = customUserMapper.createUserDtoToDefaultUser(dto);
    Contact contact = contactMapper.createUserDtoToDefaultContact(dto);
    User storedUser = userService.saveDefaultPasswordUser(defaultUser, dto.getRole(), contact);
    BaseUserDto responseUserDto = userMapper.userToBaseUserDto(storedUser);

    return ResponseEntity.created(URI.create(baseUrl + "/manage/admin/users"))
        .body(responseUserDto);
  }

  @GetMapping("/user")
  public ResponseEntity<AdminDetailsUserDto> getUsersDetails(@RequestParam("email") String email)
      throws UserNotFoundException, ContactNotFoundException {

    User userByEmail = userService.getUserByEmail(email);
    Contact contactById = contactService.getContactById(userByEmail.getId());
    AdminDetailsUserDto responseDto =
        userMapper.userToAdminDetailsUserDto(contactById, userByEmail);

    return ResponseEntity.ok(responseDto);
  }

  @GetMapping
  public ResponseEntity<Page<BaseUserDto>> getUsers(
      @RequestParam(value = "role", required = false) String role,
      @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable)
      throws AuthorityNotFoundException {

    Page<User> users =
        Objects.isNull(role)
            ? userService.getUsers(pageable)
            : userService.getUsersByRole(role, pageable);

    Page<BaseUserDto> responsePage = users.map(userMapper::userToBaseUserDto);

    return ResponseEntity.ok(responsePage);
  }

  @GetMapping("/filter")
  public ResponseEntity<Page<BaseUserDto>> getUsersByParam(
      @RequestParam("param") String param,
      @RequestParam("query") String query,
      @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable)
      throws UnsupportedParameterException {

    Page<BaseUserDto> responseUsers =
        userService.getUsersByParam(param, query, pageable).map(userMapper::userToBaseUserDto);

    return ResponseEntity.ok(responseUsers);
  }

  @DeleteMapping("/user")
  public ResponseEntity<Void> deleteCommonUsers(@RequestParam("email") String email)
      throws UserNotFoundException, NotPossibleDeleteException {

    userService.deleteCommonUserByEmail(email);

    return ResponseEntity.noContent().build();
  }

  @PutMapping("/user/role")
  public ResponseEntity<BaseUserDto> changeUserRole(
      @RequestParam("email") String email, @RequestParam("role") String role)
      throws UserNotFoundException, AuthorityNotFoundException {

    User updatedUser = userService.updateUserRole(email, role);
    BaseUserDto responseUserDto = userMapper.userToBaseUserDto(updatedUser);

    return ResponseEntity.ok(responseUserDto);
  }
}

package com.example.web.controller.users;

import com.example.exception.*;
import com.example.mapper.contact.ContactMapper;
import com.example.mapper.user.CreateUserMapper;
import com.example.mapper.user.UserMapper;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Objects;

@Validated
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
  public ResponseEntity<BaseUserDto> createUser(@RequestBody @Valid CreateUserDto dto)
      throws UserAlreadyExistException, AuthorityNotFoundException {

    User defaultUser = customUserMapper.createUserDtoToDefaultUser(dto);
    Contact contact = contactMapper.createUserDtoToDefaultContact(dto);
    User storedUser = userService.saveDefaultPasswordUser(defaultUser, dto.getRole(), contact);
    BaseUserDto responseUserDto = userMapper.userToBaseUserDto(storedUser);

    return ResponseEntity.created(URI.create(baseUrl + "/manage/admin/users"))
        .body(responseUserDto);
  }

  @GetMapping("/user")
  public ResponseEntity<AdminDetailsUserDto> getUsersDetails(
      @RequestParam("email") @NotNull(message = "The email is mandatory") String email)
      throws UserNotFoundException {

    User userByEmail = userService.getUserByEmail(email);
    AdminDetailsUserDto responseDto =
        userMapper.userToAdminDetailsUserDto(userByEmail);

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
      @RequestParam("param") @NotNull(message = "The filtering param is mandatory") String param,
      @RequestParam("query") @NotNull(message = "The filtering query is mandatory") String query,
      @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable)
      throws UnsupportedParameterException {

    Page<BaseUserDto> responseUsers =
        userService.getUsersByParam(param, query, pageable).map(userMapper::userToBaseUserDto);

    return ResponseEntity.ok(responseUsers);
  }

  @DeleteMapping("/user")
  public ResponseEntity<Void> deleteCommonUser(@RequestParam("email") @NotNull String email)
      throws UserNotFoundException, NotPossibleDeleteException {

    userService.deleteCommonUserByEmail(email);

    return ResponseEntity.noContent().build();
  }

  @PutMapping("/user/role")
  public ResponseEntity<BaseUserDto> changeUserRole(
      @RequestParam("email") @NotNull String email, @RequestParam("role") @NotNull String role)
      throws UserNotFoundException, AuthorityNotFoundException {

    User updatedUser = userService.updateUserRole(email, role);
    BaseUserDto responseUserDto = userMapper.userToBaseUserDto(updatedUser);

    return ResponseEntity.ok(responseUserDto);
  }
}

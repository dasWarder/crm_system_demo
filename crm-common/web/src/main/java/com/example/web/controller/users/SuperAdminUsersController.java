package com.example.web.controller.users;

import com.example.exception.AuthorityNotFoundException;
import com.example.exception.UnsupportedParameterException;
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
  public ResponseEntity<SuperAdminUserDetailsDto> getUserDetails(
      @RequestParam("email") String email) throws UserNotFoundException {

    User userByEmail = userService.getUserByEmail(email);
    SuperAdminUserDetailsDto responseDto = userMapper.userToSuperAdminUserDetailsDto(userByEmail);

    return ResponseEntity.ok(responseDto);
  }

  @PutMapping("/user/role")
  public ResponseEntity<BaseUserDto> updateUserRole(
      @RequestParam("email") String email, @RequestParam("role") String role)
      throws UserNotFoundException, AuthorityNotFoundException {

    User updateUser = userService.updateUserRole(email, role);
    BaseUserDto dto = userMapper.userToBaseUserDto(updateUser);

    return ResponseEntity.ok(dto);
  }

  @DeleteMapping("/user")
  public ResponseEntity<Void> deleteUser(@RequestParam("email") String email) {

    userService.deleteUserByEmail(email);

    return ResponseEntity.noContent().build();
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
}

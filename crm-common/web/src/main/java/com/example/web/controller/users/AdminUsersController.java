package com.example.web.controller.users;

import com.example.mapper.dto.user.admin.CreateUserDto;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manage/admin/users")
public class AdminUsersController {

  private final UserService userService;


  @PostMapping("/user")
  public ResponseEntity createUser(@RequestBody CreateUserDto dto) {

    return null;
  }

  @GetMapping("/user")
  public ResponseEntity getUsersDetails(@RequestParam("email") String email) {

    return null;
  }

  @PutMapping("/user")
  public ResponseEntity updateUser(
      @RequestParam("email") String email, @RequestBody UpdateUserDetailsDto dto) {

    return null;
  }

  @GetMapping
  public ResponseEntity getUsers(@RequestParam(value = "role", required = false) String role,
      @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

    return null;
  }

  @DeleteMapping("/user")
  public ResponseEntity deleteCommonUsers(@RequestParam("email") String email) {

    return null;
  }

  @PutMapping("/user/role")
  public ResponseEntity changeUserRole(@RequestParam("email") String email, @RequestParam("role") String role) {


    return null;
  }

}

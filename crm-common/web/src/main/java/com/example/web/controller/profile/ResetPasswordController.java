package com.example.web.controller.profile;

import com.example.exception.PasswordResetTokenNotFoundException;
import com.example.exception.ResetTokenExpiryException;
import com.example.exception.UserNotFoundException;
import com.example.exception.WrongPasswordException;
import com.example.mapper.ResetTokenMapper;
import com.example.mapper.UserMapper;
import com.example.mapper.dto.user.BaseUserDto;
import com.example.mapper.dto.user.ResetPasswordDto;
import com.example.mapper.dto.user.token.ResetTokenDto;
import com.example.model.user.User;
import com.example.service.user.ForgotPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ResetPasswordController {

  private final UserMapper userMapper;

  private final ResetTokenMapper tokenMapper;

  private final ForgotPasswordService forgotPasswordService;

  @PostMapping
  public ResponseEntity<Void> processForgotPassword(@RequestBody BaseUserDto dto)
      throws UserNotFoundException {

    forgotPasswordService.processForgotPassword(dto.getEmail());

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/reset")
  public ResponseEntity<ResetTokenDto> resetTokenValidation(@RequestParam("token") String token) throws PasswordResetTokenNotFoundException, ResetTokenExpiryException {

    String resetToken = forgotPasswordService.directResetPassword(token);
    ResetTokenDto resetTokenDto = tokenMapper.stringToResetTokenDto(resetToken);

    return ResponseEntity.ok(resetTokenDto);
  }

  @PutMapping("/reset")
  public ResponseEntity<BaseUserDto> resetPassword(@RequestBody ResetPasswordDto dto) throws UserNotFoundException, PasswordResetTokenNotFoundException, WrongPasswordException {

    User updatedUser = forgotPasswordService.resetUserPassword(dto.getPassword(), dto.getConfirmPassword(), dto.getToken());
    BaseUserDto baseUserDto = userMapper.userToBaseUserDto(updatedUser);

    return ResponseEntity.ok(baseUserDto);
  }
}

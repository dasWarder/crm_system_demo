package com.example.web.controller.login;

import com.example.exception.AuthorityNotFoundException;
import com.example.exception.UserAlreadyExistException;
import com.example.mapper.TokenMapper;
import com.example.mapper.UserMapper;
import com.example.mapper.UserMapperWithAuthority;
import com.example.mapper.dto.user.AuthUserDto;
import com.example.mapper.dto.user.BaseUserDto;
import com.example.mapper.dto.user.SaveUserDto;
import com.example.mapper.dto.user.token.TokenDto;
import com.example.mapper.dto.user.token.TokenRefreshRequest;
import com.example.model.user.Token;
import com.example.model.user.User;
import com.example.service.security.UserDetailsSecurityService;
import com.example.service.user.UserService;
import com.example.service.user.token.TokenService;
import com.example.web.config.security.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

  private final TokenMapper tokenMapper;

  private final TokenProvider tokenProvider;

  private final AuthenticationManager authenticationManager;

  private final UserDetailsSecurityService securityService;

  private final TokenService tokenService;

  private final UserMapper userMapper;

  private final UserService userService;

  private final UserMapperWithAuthority customMapper;

  private static final String BASE_URL = "http://localhost:8080/registration";

  @PostMapping("/registration/common")
  public ResponseEntity<BaseUserDto> registrationNewUser(
      @RequestBody @Valid @NotNull SaveUserDto dto)
      throws AuthorityNotFoundException, UserAlreadyExistException {

    User userToStore = customMapper.saveUserDtoToUser(dto);
    User storedUser = userService.saveUser(userToStore);
    BaseUserDto responseUser = userMapper.userToBaseUserDto(storedUser);

    return ResponseEntity.created(URI.create(BASE_URL)).body(responseUser);
  }

  @PostMapping("/auth")
  public ResponseEntity<TokenDto> auth(@RequestBody @Valid @NotNull AuthUserDto dto)
      throws Throwable {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

    UserDetails details = securityService.loadUserByUsername(dto.getEmail());
    String token = tokenProvider.generateToken(details.getUsername());

    tokenService.deleteTokenBySubject(dto.getEmail());
    Token refreshToken = tokenService.createToken(details.getUsername());
    TokenDto tokenDto = tokenMapper.fromStringsToToken(token, refreshToken.getToken());

    return ResponseEntity.created(URI.create("/auth")).body(tokenDto);
  }

  @PostMapping("/refresh")
  public ResponseEntity<TokenDto> refreshToken(
      @RequestBody @Valid @NotNull TokenRefreshRequest requestDto) throws Throwable {

    String refreshToken = requestDto.getRefreshToken();

    Token tokenFromDb = tokenService.findTokenByToken(refreshToken);
    Token validToken = tokenService.verifyExpiration(tokenFromDb);

    String subject = validToken.getSubject();
    String token = tokenProvider.generateToken(subject);

    TokenDto responseToken = tokenMapper.fromStringsToToken(token, validToken.getToken());

    return ResponseEntity.ok(responseToken);
  }
}

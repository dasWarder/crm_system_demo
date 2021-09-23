package com.example.service.user.token;

import com.example.exception.TokenNotFoundException;
import com.example.exception.TokenRefreshException;
import com.example.exception.UserNotFoundException;
import com.example.model.user.Token;
import com.example.repository.TokenRepository;
import com.example.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.example.service.user.UserTestData.TEST_USER_1;
import static com.example.service.user.token.TokenTestData.TEST_EXP_TOKEN;
import static com.example.service.user.token.TokenTestData.TEST_TOKEN_1;

@Slf4j
class TokenServiceImplTest {

  private long expirationTime = 10000;

  private final UserService userService = Mockito.mock(UserService.class);

  private final TokenRepository tokenRepository = Mockito.mock(TokenRepository.class);

  private final TokenService tokenService = new TokenServiceImpl(userService, tokenRepository);

  @Test
  public void shouldCreateTokenProperly() throws UserNotFoundException {

    log.info("Test createToken() method");

    Mockito.when(userService.getUserByEmail(TEST_USER_1.getEmail())).thenReturn(TEST_USER_1);
    Token storedToken = tokenService.createToken(TEST_USER_1.getEmail());
  }

  @Test
  public void shouldFindTokenByToken() throws TokenNotFoundException {

    log.info("Test findTokenByToken() method");

    Mockito.when(tokenRepository.getTokenByToken(TEST_TOKEN_1.getToken()))
        .thenReturn(Optional.of(TEST_TOKEN_1));

    Token tokenByToken = tokenService.findTokenByToken(TEST_TOKEN_1.getToken());

    Assertions.assertNotNull(tokenByToken);
    Assertions.assertEquals(TEST_TOKEN_1, tokenByToken);
  }

  @Test
  public void shouldVerifyExpirationProperly() throws TokenRefreshException {

    log.info("Test verifyExpiration() method");

    Token token = tokenService.verifyExpiration(TEST_TOKEN_1);

    Assertions.assertNotNull(token);
    Assertions.assertEquals(TEST_TOKEN_1, token);
  }

  @Test
  public void shouldThrowExceptionWhenVerifyExpirationNonValidProperly() {

    log.info(
        "Test verifyExpiration() method throws an exception when the expiration time of a token already gone");
    Assertions.assertThrows(
        TokenRefreshException.class, () -> tokenService.verifyExpiration(TEST_EXP_TOKEN));
  }

  @Test
  public void shouldDeleteTokenByIdProperly() {

    log.info("Test deleteTokenById() method");
    tokenService.deleteTokenById(TEST_TOKEN_1.getId());
  }

  @Test
  public void shouldDeleteTokenBySubjectProperly() {

    log.info("Test deleteTokenBySubject() method");
    tokenService.deleteTokenBySubject(TEST_TOKEN_1.getSubject());
  }
}

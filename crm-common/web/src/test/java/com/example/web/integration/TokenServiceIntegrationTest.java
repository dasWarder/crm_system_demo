package com.example.web.integration;

import com.example.exception.TokenNotFoundException;
import com.example.exception.TokenRefreshException;
import com.example.exception.UserNotFoundException;
import com.example.model.user.Token;
import com.example.service.user.token.TokenService;
import com.example.web.AbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static com.example.web.data.TestTokenData.TEST_TOKEN_1;
import static com.example.web.data.TestUserData.TEST_USER_2;

@Slf4j
@Sql(
    scripts = {
      "classpath:/db/todoList/populate_todo_related_tables.sql",
      "classpath:/db/token/populate_refresh_token_table.sql"
    })
public class TokenServiceIntegrationTest extends AbstractTest {

  @Autowired private TokenService tokenService;

  @Test
  public void shouldCreateTokenProperly() throws UserNotFoundException {

    log.info("Test createToken() method");

    Token token = tokenService.createToken(TEST_USER_2.getEmail());

    Assert.assertNotNull(token);
    Assert.assertEquals(TEST_USER_2.getEmail(), token.getSubject());
  }

  @Test
  public void shouldFindTokenByTokenProperly() throws TokenNotFoundException {

    log.info("Test findTokenByToken() method");

    Token tokenByToken = tokenService.findTokenByToken(TEST_TOKEN_1.getToken());

    Assert.assertNotNull(tokenByToken);
    Assert.assertEquals(TEST_TOKEN_1.getToken(), tokenByToken.getToken());
  }

  @Test
  public void shouldVerifyExpirationProperly() throws TokenRefreshException {

    log.info("Test verifyExpiration() method");

    Token token = tokenService.verifyExpiration(TEST_TOKEN_1);

    Assert.assertNotNull(token);
    Assert.assertEquals(TEST_TOKEN_1, token);
  }

  @Test
  public void shouldDeleteTokenByIdProperly() throws TokenNotFoundException {

    log.info("Test deleteTokenById() method");

    Token tokenByToken = tokenService.findTokenByToken(TEST_TOKEN_1.getToken());
    Assert.assertNotNull(tokenByToken);

    tokenService.deleteTokenById(TEST_TOKEN_1.getId());
    Assert.assertThrows(
        TokenNotFoundException.class, () -> tokenService.findTokenByToken(TEST_TOKEN_1.getToken()));
  }

  @Test
  public void shouldDeleteTokenBySubjectProperly() throws TokenNotFoundException {

    log.info("Test deleteTokenBySubject() method");

    Token tokenByToken = tokenService.findTokenByToken(TEST_TOKEN_1.getToken());
    Assert.assertNotNull(tokenByToken);

    tokenService.deleteTokenBySubject(TEST_TOKEN_1.getSubject());
    Assert.assertThrows(
        TokenNotFoundException.class, () -> tokenService.findTokenByToken(TEST_TOKEN_1.getToken()));
  }
}

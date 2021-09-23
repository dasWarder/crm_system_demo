package com.example.service.user.token;

import com.example.model.user.Token;

import java.time.Instant;
import java.util.UUID;

public class TokenTestData {

  public static final Token TEST_TOKEN_1 =
      new Token(
          1L, "jack@gmail.com", UUID.randomUUID().toString(), Instant.now().plusSeconds(60 * 3));

  public static final Token TEST_SAVE_TOKEN =
      new Token(
          2L, "dave@gmail.com", UUID.randomUUID().toString(), Instant.now().plusSeconds(60 * 3));

  public static final Token TEST_EXP_TOKEN =
      new Token(
          3L, "test@gmail.com", UUID.randomUUID().toString(), Instant.now().minusSeconds(60 * 3));
}

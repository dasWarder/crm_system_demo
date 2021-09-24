package com.example.web.data;

import com.example.model.user.Token;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TestTokenData {

  public static final Token TEST_TOKEN_1 =
      Token.builder()
          .id(1L)
          .subject("test@gmail.com")
          .token("c1ac32f3-86ac-418f-8c02-72ab4d41ac2d")
          .expiryDate(LocalDateTime.of(2030, 1, 1, 12, 0, 0).toInstant(ZoneOffset.UTC))
          .build();
}

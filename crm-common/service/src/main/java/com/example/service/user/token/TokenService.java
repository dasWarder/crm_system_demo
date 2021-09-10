package com.example.service.user.token;

import com.example.exception.TokenNotFoundException;
import com.example.exception.TokenRefreshException;
import com.example.exception.UserNotFoundException;
import com.example.model.user.Token;

public interface TokenService {

  Token createToken(String username) throws UserNotFoundException;

  Token findTokenByToken(String token) throws TokenNotFoundException;

  Token verifyExpiration(Token token) throws TokenRefreshException;

  void deleteTokenById(Long id);

  void deleteTokenBySubject(String subject);
}

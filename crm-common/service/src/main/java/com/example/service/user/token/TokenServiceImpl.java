package com.example.service.user.token;

import com.example.exception.TokenNotFoundException;
import com.example.exception.TokenRefreshException;
import com.example.exception.UserNotFoundException;
import com.example.model.user.User;
import com.example.repository.TokenRepository;
import com.example.repository.UserRepository;
import com.example.model.user.Token;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

  @Value("${jwt.refreshExpirationMs}")
  private long expirationTime;

  private final UserService userService;

  private final TokenRepository tokenRepository;

  @Override
  @Transactional
  public Token createToken(String username) throws UserNotFoundException {

    log.info("Create a new refresh token for a user with email = {}", username);
    User validUser = userService.getUserByEmail(username);
    Token token =
        Token.builder()
            .subject(validUser.getEmail())
            .expiryDate(Instant.now().plusMillis(expirationTime))
            .token(UUID.randomUUID().toString())
            .build();
    Token storedToken = tokenRepository.save(token);

    return storedToken;
  }

  @Override
  @Transactional(readOnly = true)
  public Token findTokenByToken(String token) throws TokenNotFoundException {

    log.info("Get a token by a token = {}", token);
    Token validToken =
        tokenRepository
            .getTokenByToken(token)
            .orElseThrow(
                () ->
                    new TokenNotFoundException(
                        String.format("A token with a token %s not found", token)));
    return validToken;
  }

  @Override
  @Transactional
  public Token verifyExpiration(Token token) throws TokenRefreshException {

    log.info("Validate a token for the email= {} for expiration", token.getToken());

    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {

      tokenRepository.delete(token);

      throw new TokenRefreshException(
          String.format("Refresh for the email %s token was expired", token.getSubject()));
    }

    return token;
  }

  @Override
  public void deleteTokenById(Long id) {

    log.info("Delete a token by its id = {}", id);
    tokenRepository.deleteById(id);
  }

  @Override
  public void deleteTokenBySubject(String subject) {

    log.info("Delete a token by its subject = {}", subject);
    tokenRepository.deleteTokenBySubject(subject);
  }
}

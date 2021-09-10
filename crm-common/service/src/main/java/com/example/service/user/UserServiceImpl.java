package com.example.service.user;

import com.example.exception.AuthorityNotFoundException;
import com.example.exception.UserAlreadyExistException;
import com.example.exception.UserNotFoundException;
import com.example.model.user.User;
import com.example.model.user.UserAuthority;
import com.example.repository.UserRepository;
import com.example.service.user.authority.AuthorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final AuthorityService authorityService;

  @Override
  @Transactional
  public User saveUser(User user) throws UserAlreadyExistException, AuthorityNotFoundException {

    log.info("Store a new user");
    checkUserAlreadyExistOrThrowException(user);
    UserAuthority userRole = authorityService.getUserAuthorityByAuthorityName("USER");
    user.setRole(userRole);

    User storedUser = userRepository.save(user);

    return storedUser;
  }

  @Override
  @Transactional(readOnly = true)
  public User getUserByEmail(String email) throws UserNotFoundException {

    log.info("Get a user by the email = {}", email);
    User userByEmail =
        userRepository
            .getUserByEmail(email)
            .orElseThrow(
                () -> new UserNotFoundException(String.format("A user with email = %s not found", email)));
    return userByEmail;
  }

  @Override
  @Transactional
  public User updateUserByEmail(String email, User user) throws UserNotFoundException {

    log.info("Update a user with email = {}", email);
    User dbUser =
        userRepository
            .getUserByEmail(email)
            .orElseThrow(
                () -> new UserNotFoundException(String.format("A user with email = %s not found")));
    user.setId(dbUser.getId());
    User updatedUser = userRepository.save(user);

    return updatedUser;
  }

  @Override
  @Transactional
  public void deleteUserByEmail(String email) {

    log.info("Delete a user by email = {}", email);
    userRepository.deleteUserByEmail(email);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<User> getUsers(Pageable pageable) {

    log.info("Get a page of users");
    Page<User> users = userRepository.findAll(pageable);

    return users;
  }

  private void checkUserAlreadyExistOrThrowException(User user) throws UserAlreadyExistException {

    User validUser = userRepository.getUserByEmail(user.getEmail()).orElse(null);
    if (!Objects.isNull(validUser)) {
      throw new UserAlreadyExistException(
          String.format("The user with this email = %s already exist", user.getEmail()));
    }
  }
}

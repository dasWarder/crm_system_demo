package com.example.service.user;

import com.example.exception.*;
import com.example.model.user.User;
import com.example.model.user.UserAuthority;
import com.example.repository.UserRepository;
import com.example.service.notification.EmailNotificationService;
import com.example.service.user.authority.AuthorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  @Value("${user.password.default}")
  private String defaultPassword;

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final AuthorityService authorityService;

  private final EmailNotificationService mailService;

  private static final String REGISTER_SUBJECT = "Registration";

  private static final String REGISTRATION_MESSAGE =
      "Dear, %s! \n\nThank you for registration on our service. \n\nWith kind regards, CRM Team";

  @Override
  public User getCurrentUser() throws UserNotFoundException {

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String authEmail =
        principal instanceof UserDetails
            ? ((UserDetails) principal).getUsername()
            : principal.toString();

    User userByEmail =
        userRepository
            .getUserByEmail(authEmail)
            .orElseThrow(
                () ->
                    new UserNotFoundException(
                        String.format("User with email %s not found", authEmail)));

    return userByEmail;
  }

  @Override
  @Transactional
  public User saveUser(User user) throws UserAlreadyExistException, AuthorityNotFoundException {

    log.info("Store a new user");
    checkUserAlreadyExistOrThrowException(user);
    UserAuthority userRole = authorityService.getUserAuthorityByAuthorityName("USER");
    user.setRole(userRole);

    User storedUser = userRepository.save(user);
    String message = String.format(REGISTRATION_MESSAGE, user.getEmail());
    mailService.sendNotification(user.getEmail(), message, REGISTER_SUBJECT);

    return storedUser;
  }

  @Override
  public User saveDefaultPasswordUser(User user, String role) throws UserAlreadyExistException, AuthorityNotFoundException {

    log.info("Store a user with default password and role = {}", role);
    UserAuthority authority = authorityService.getUserAuthorityByAuthorityName(role);
    user.setRole(authority);
    user.setPassword(passwordEncoder.encode(defaultPassword));
    user.setRegistrationDate(LocalDate.now());
    User storedUser = this.saveUser(user);

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
                () ->
                    new UserNotFoundException(
                        String.format("A user with email = %s not found", email)));
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
  public User updateUserPassByEmail(String email, String oldPass, String newPass)
      throws UserNotFoundException, WrongPasswordException {

    log.info("Update a pass for a user with the email = {}", email);
    User userByEmail = this.getUserByEmail(email);

    if (!passwordEncoder.matches(oldPass, userByEmail.getPassword())) {
      throw new WrongPasswordException("Wrong password was input");
    }

    String updatedPass = passwordEncoder.encode(newPass);
    userByEmail.setPassword(updatedPass);
    User updatedUser = userRepository.save(userByEmail);

    return updatedUser;
  }

  @Override
  public User updateUserEmail(String oldEmail, String email) throws UserNotFoundException {

    log.info("Update an email for a user with a current email = {}", oldEmail);
    User userByEmail = this.getUserByEmail(oldEmail);

    userByEmail.setEmail(email);
    userByEmail.getContact().setEmail(email);
    User updatedUser = userRepository.save(userByEmail);

    return updatedUser;
  }

  @Override
  @Transactional
  public void deleteUserByEmail(String email) {

    log.info("Delete a user by email = {}", email);
    userRepository.deleteUserByEmail(email);
  }

  @Override
  public void deleteCommonUserByEmail(String email) throws UserNotFoundException, NotPossibleDeleteException {

    log.info("Delete a common user by an email = {}", email);
    User user = this.getUserByEmail(email);
    String role = user.getRole().getAuthority();

    if (role.equals("ADMIN") || role.equals("SUPER_ADMIN")) {
      throw new NotPossibleDeleteException(String.format("The user %s with role = %s can't be removed", email, role));
    }

    userRepository.deleteUserByEmail(email);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<User> getUsers(Pageable pageable) {

    log.info("Get a page of users");
    Page<User> users = userRepository.findAll(pageable);

    return users;
  }

  @Override
  @Transactional(readOnly = true)
  public Page<User> getUsersByRole(String role, Pageable pageable)
      throws AuthorityNotFoundException {

    log.info("Get a page of users by role");
    UserAuthority authorityByName = authorityService.getUserAuthorityByAuthorityName(role);
    Page<User> usersByRole =
        userRepository.getUsersByRole_Authority(authorityByName.getAuthority(), pageable);

    return usersByRole;
  }

  @Override
  public User updateUserRole(String email, String role) throws UserNotFoundException, AuthorityNotFoundException {

    log.info("Update a role = {} for a user with the email = {}", role, email);
    User user =
        userRepository
            .getUserByEmail(email)
            .orElseThrow(
                () ->
                    new UserNotFoundException(
                        String.format("The user with the email = %s not found", email)));
    UserAuthority authority = authorityService.getUserAuthorityByAuthorityName(role);
    user.setRole(authority);
    User updatedUser = userRepository.save(user);

    return updatedUser;
  }

  private void checkUserAlreadyExistOrThrowException(User user) throws UserAlreadyExistException {

    User validUser = userRepository.getUserByEmail(user.getEmail()).orElse(null);
    if (!Objects.isNull(validUser)) {
      throw new UserAlreadyExistException(
          String.format("The user with this email = %s already exist", user.getEmail()));
    }
  }
}

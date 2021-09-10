package com.example.service.user;

import com.example.exception.AuthorityNotFoundException;
import com.example.exception.UserAlreadyExistException;
import com.example.exception.UserNotFoundException;
import com.example.model.user.User;
import com.example.repository.UserRepository;
import com.example.service.user.authority.AuthorityService;
import com.example.service.user.authority.AuthorityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.service.user.UserTestData.*;

@Slf4j
class UserServiceImplTest {

  private final UserRepository userRepository = Mockito.mock(UserRepository.class);

  private final AuthorityService authorityService = Mockito.mock(AuthorityServiceImpl.class);

  private final UserService userService = new UserServiceImpl(userRepository, authorityService);

  @Test
  public void shouldSaveUserProperly()
      throws AuthorityNotFoundException, UserAlreadyExistException {

    log.info("Test saveUser() method");
    Mockito.when(authorityService.getUserAuthorityByAuthorityName(TEST_AUTHORITY.getAuthority()))
        .thenReturn(TEST_AUTHORITY);
    Mockito.when(userRepository.getUserByEmail(TEST_SAVE_USER.getEmail()))
        .thenReturn(Optional.ofNullable(null));
    Mockito.when(userRepository.save(TEST_SAVE_USER)).thenReturn(TEST_SAVE_USER);

    User storedUser = userService.saveUser(TEST_SAVE_USER);

    Assertions.assertNotNull(storedUser);
    Assertions.assertEquals(TEST_SAVE_USER, storedUser);
  }

  @Test
  public void shouldGetUserByEmailProperly() throws UserNotFoundException {

    log.info("Test getUserByEmail() method");
    Mockito.when(userRepository.getUserByEmail(TEST_USER_1.getEmail()))
        .thenReturn(Optional.of(TEST_USER_1));

    User userByEmail = userService.getUserByEmail(TEST_USER_1.getEmail());

    Assertions.assertNotNull(userByEmail);
    Assertions.assertEquals(TEST_USER_1, userByEmail);
  }

  @Test
  public void shouldThrowExceptionGetUserByEmailWhenEmailWrongOrNull() {

    log.info("Test getUserByEmail() method throws an exception when an email is wrong or null");
    final String wrongEmail = "wrong@gmail.com";

    Assertions.assertThrows(
        UserNotFoundException.class, () -> userService.getUserByEmail(wrongEmail));
    Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail(null));
  }

  @Test
  public void shouldUpdateUserByEmailProperly() throws UserNotFoundException {

    log.info("Test updateUserByEmail() method");

    Mockito.when(userRepository.getUserByEmail(TEST_USER_2.getEmail()))
        .thenReturn(Optional.of(TEST_USER_2));
    Mockito.when(userRepository.save(TEST_UPDATE_USER)).thenReturn(TEST_UPDATE_USER);

    User updateUser = userService.updateUserByEmail(TEST_USER_2.getEmail(), TEST_UPDATE_USER);

    Assertions.assertNotNull(updateUser);
    Assertions.assertEquals(TEST_UPDATE_USER, updateUser);
  }

  @Test
  public void shouldDeleteUserByEmailProperly() {

    log.info("Test deleteUserByEmail() method");
    userService.deleteUserByEmail(TEST_USER_2.getEmail());
  }

  @Test
  public void shouldGetUsersProperly() {

    log.info("Test getUsers() method");
    final Pageable pageable = PageRequest.of(0, 20);
    List<User> expectedUsers = Stream.of(TEST_USER_1, TEST_USER_2).collect(Collectors.toList());
    PageImpl<User> usersPage = new PageImpl<>(expectedUsers);
    Mockito.when(userRepository.findAll(pageable)).thenReturn(usersPage);

    Page<User> actualUsersPage = userService.getUsers(pageable);

    Assertions.assertNotNull(actualUsersPage);
    Assertions.assertEquals(expectedUsers, actualUsersPage.getContent());
  }
}

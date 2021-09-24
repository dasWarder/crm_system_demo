package com.example.web.integration;

import com.example.exception.AuthorityNotFoundException;
import com.example.exception.UserAlreadyExistException;
import com.example.exception.UserNotFoundException;
import com.example.exception.WrongPasswordException;
import com.example.model.user.User;
import com.example.service.user.UserService;
import com.example.web.AbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static com.example.web.data.TestUserData.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Sql(scripts = {"classpath:/db/todoList/populate_todo_related_tables.sql"})
public class UserServiceIntegrationTest extends AbstractTest {

  @Autowired private UserService userService;


  @Test
  @WithMockUser(username = "test@gmail.com", authorities = "USER")
  public void shouldGetCurrentUserProperly() throws UserNotFoundException {

    log.info("Test getCurrentUser() method");

    User currentUser = userService.getCurrentUser();

    Assert.assertNotNull(currentUser);
    assertThat(currentUser)
        .usingRecursiveComparison()
        .ignoringFields("password", "role", "registrationDate", "todoList", "contact", "reports", "enabled")
        .isEqualTo(TEST_USER_1);
  }

  @Test
  public void shouldSaveUserProperly()
      throws UserAlreadyExistException, AuthorityNotFoundException {

    log.info("Test saveUser() method");

    User storedUser = userService.saveUser(TEST_SAVE_USER);
    Assert.assertNotNull(storedUser);

    assertThat(storedUser)
        .usingRecursiveComparison()
        .ignoringFields("role", "registrationDate")
        .isEqualTo(TEST_SAVE_USER);
  }

  @Test
  public void shouldUpdateUserByEmailProperly() throws UserNotFoundException {

    log.info("Test updateUserByEmail() method");

    User updatedUser = userService.updateUserByEmail(TEST_USER_2.getEmail(), TEST_UPDATE_USER);

    Assert.assertNotNull(updatedUser);
    assertThat(updatedUser)
        .usingRecursiveComparison()
        .ignoringFields("role", "registrationDate", "todoList", "contact", "reports", "enabled")
        .isEqualTo(TEST_UPDATE_USER);
  }

  @Test
  public void shouldUpdateUserPassByEmailProperly() throws UserNotFoundException, WrongPasswordException {

    log.info("Test updateUserPassByEmail() method");

    String newPass = "updatedPass";
    User updatedPassUser = userService.updateUserPassByEmail(TEST_USER_1.getEmail(), TEST_USER_1.getPassword(), newPass);

    Assert.assertNotNull(updatedPassUser);
  }


  @Test
  public void shouldGetUserByEmailProperly() throws UserNotFoundException {

    log.info("Test getUserByEmail() method");

    User userByEmail = userService.getUserByEmail(TEST_USER_1.getEmail());

    Assert.assertNotNull(userByEmail);
    assertThat(userByEmail)
        .usingRecursiveComparison()
        .ignoringFields("password", "role", "registrationDate", "todoList", "contact", "reports", "enabled")
        .isEqualTo(TEST_USER_1);
  }

  @Test
  public void shouldDeleteUserByEmailProperly() throws UserNotFoundException {

    log.info("Test deleteUserByEmail() method");

    User userByEmail = userService.getUserByEmail(TEST_USER_1.getEmail());

    Assert.assertNotNull(userByEmail);

    userService.deleteUserByEmail(TEST_USER_1.getEmail());
    Assert.assertThrows(
        UserNotFoundException.class, () -> userService.getUserByEmail(TEST_USER_1.getEmail()));
  }

  @Test
  public void shouldGetUsersProperly() {

    log.info("Test getUsers() method");

    Pageable pageable = PageRequest.of(0, 20);
    Page<User> users = userService.getUsers(pageable);

    Assert.assertNotNull(users);
    Assert.assertEquals(2, users.getContent().size());
  }
}

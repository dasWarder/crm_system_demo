package com.example.web.integration;

import com.example.exception.AuthorityNotFoundException;
import com.example.model.user.UserAuthority;
import com.example.service.user.authority.AuthorityService;
import com.example.web.AbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static com.example.web.data.TestAuthorityData.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Sql(
    scripts = {
      "classpath:/db/todoList/populate_todo_related_tables.sql",
      "classpath:/db/token/populate_refresh_token_table.sql"
    })
public class AuthorityServiceIntegrationTest extends AbstractTest {

  @Autowired private AuthorityService authorityService;

  @Test
  public void shouldSaveUserAuthorityProperly() {

    log.info("Test saveUserAuthority() method");

    UserAuthority userAuthority = authorityService.saveUserAuthority(TEST_SAVE_AUTHORITY);

    Assert.assertNotNull(userAuthority);
    assertThat(userAuthority).usingRecursiveComparison().isEqualTo(TEST_SAVE_AUTHORITY);
  }

  @Test
  public void shouldUpdateUserAuthorityByAuthorityNameProperly() throws AuthorityNotFoundException {

    log.info("Test updateUserAuthorityByAuthorityName() method");

    UserAuthority userAuthority =
        authorityService.updateUserAuthorityByAuthorityName(
            TEST_AUTHORITY_2.getAuthority(), TEST_UPDATE_AUTHORITY);

    Assert.assertNotNull(userAuthority);
    assertThat(userAuthority).usingRecursiveComparison().isEqualTo(TEST_UPDATE_AUTHORITY);
  }

  @Test
  public void shouldGetUserAuthorityByAuthorityNameProperly() throws AuthorityNotFoundException {

    log.info("Test getUserAuthorityByAuthorityName() method");
    UserAuthority user =
        authorityService.getUserAuthorityByAuthorityName(TEST_AUTHORITY_1.getAuthority());

    Assert.assertNotNull(user);
    assertThat(user).usingRecursiveComparison().ignoringFields("users").isEqualTo(TEST_AUTHORITY_1);
  }

  @Test
  public void shouldDeleteUserAuthorityByAuthorityNameProperly() throws AuthorityNotFoundException {

    log.info("Test deleteUserAuthorityByAuthorityName() method");

    UserAuthority user =
        authorityService.getUserAuthorityByAuthorityName(TEST_AUTHORITY_1.getAuthority());
    Assert.assertNotNull(user);

    authorityService.deleteUserAuthorityByAuthorityName(TEST_AUTHORITY_1.getAuthority());
    Assert.assertThrows(
        AuthorityNotFoundException.class,
        () -> authorityService.getUserAuthorityByAuthorityName(TEST_AUTHORITY_1.getAuthority()));
  }
}

package com.example.service.user.authority;

import com.example.exception.AuthorityNotFoundException;
import com.example.model.user.UserAuthority;
import com.example.repository.AuthorityRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.example.service.user.authority.AuthorityTestData.*;

@Slf4j
class AuthorityServiceImplTest {

  private final AuthorityRepository authorityRepository = Mockito.mock(AuthorityRepository.class);

  private final AuthorityService authorityService = new AuthorityServiceImpl(authorityRepository);

  @Test
  public void shouldSaveUserAuthorityProperly() {

    log.info("Test saveUserAuthority() method");

    Mockito.when(authorityRepository.save(TEST_SAVE_AUTHORITY)).thenReturn(TEST_SAVE_AUTHORITY);
    UserAuthority storedAuthority = authorityService.saveUserAuthority(TEST_SAVE_AUTHORITY);

    Assertions.assertNotNull(storedAuthority);
    Assertions.assertEquals(TEST_SAVE_AUTHORITY, storedAuthority);
  }

  @Test
  public void shouldUpdateUserAuthorityByAuthorityNameProperly() throws AuthorityNotFoundException {

    log.info("Test updateUserAuthorityByAuthorityName() method");

    Mockito.when(authorityRepository.getUserAuthorityByAuthority(TEST_AUTHORITY_1.getAuthority()))
        .thenReturn(Optional.of(TEST_AUTHORITY_1));
    Mockito.when(authorityRepository.save(TEST_UPDATE_AUTHORITY)).thenReturn(TEST_UPDATE_AUTHORITY);

    UserAuthority updated =
        authorityService.updateUserAuthorityByAuthorityName(
            TEST_AUTHORITY_1.getAuthority(), TEST_UPDATE_AUTHORITY);

    Assertions.assertNotNull(updated);
    Assertions.assertEquals(TEST_UPDATE_AUTHORITY, updated);
  }

  @Test
  public void shouldGetUserAuthorityByAuthorityNameProperly() throws AuthorityNotFoundException {

    log.info("Test getUserAuthorityByAuthorityName() method");

    Mockito.when(authorityRepository.getUserAuthorityByAuthority(TEST_AUTHORITY_1.getAuthority()))
        .thenReturn(Optional.of(TEST_AUTHORITY_1));

    UserAuthority byName =
        authorityService.getUserAuthorityByAuthorityName(TEST_AUTHORITY_1.getAuthority());

    Assertions.assertNotNull(byName);
    Assertions.assertEquals(TEST_AUTHORITY_1, byName);
  }

  @Test
  public void shouldThrowExceptionWhenGetUserAuthorityByAuthorityWithNullName() {

    log.info(
        "Test getUserAuthorityByAuthorityName() method throws an exception when an authority name is null or wrong");

    Assertions.assertThrows(
        AuthorityNotFoundException.class,
        () -> authorityService.getUserAuthorityByAuthorityName(null));
  }

  @Test
  public void shouldDeleteUserAuthorityByAuthorityNameProperly() {

    log.info("Test deleteUserAuthorityByAuthorityName() method");

    authorityService.deleteUserAuthorityByAuthorityName(TEST_AUTHORITY_1.getAuthority());
  }
}

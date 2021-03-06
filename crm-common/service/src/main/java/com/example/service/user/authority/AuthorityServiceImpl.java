package com.example.service.user.authority;

import com.example.exception.AuthorityNotFoundException;
import com.example.model.user.User;
import com.example.model.user.UserAuthority;
import com.example.repository.AuthorityRepository;
import com.example.repository.UserRepository;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

  private final AuthorityRepository authorityRepository;

  private final UserRepository userRepository;

  @Override
  @Transactional
  public UserAuthority saveUserAuthority(UserAuthority authority) {

    log.info("Store a new user authority");
    UserAuthority storedAuthority = authorityRepository.save(authority);

    return storedAuthority;
  }

  @Override
  @Transactional
  public UserAuthority updateUserAuthorityByAuthorityName(
      String authority, UserAuthority updateAuthority) throws AuthorityNotFoundException {

    log.info("Update user authority with a name = {}", authority);
    UserAuthority dbAuthority =
        authorityRepository
            .getUserAuthorityByAuthority(authority)
            .orElseThrow(
                () ->
                    new AuthorityNotFoundException(
                        String.format("The authority with a name %s not found", authority)));
    updateAuthority.setId(dbAuthority.getId());
    updateAuthority.setUsers(dbAuthority.getUsers());
    UserAuthority storedAuthority = authorityRepository.save(updateAuthority);

    return storedAuthority;
  }

  @Override
  @Transactional(readOnly = true)
  public UserAuthority getUserAuthorityByAuthorityName(String authority)
      throws AuthorityNotFoundException {

    log.info("Get authority by an authority name = {}", authority);
    UserAuthority userAuthorityByAuthorityName =
        authorityRepository
            .getUserAuthorityByAuthority(authority)
            .orElseThrow(
                () ->
                    new AuthorityNotFoundException(
                        String.format("The authority with a name %s not found", authority)));
    return userAuthorityByAuthorityName;
  }

  @Override
  @Transactional
  public void deleteUserAuthorityByAuthorityName(String authority) {

    log.info("Delete user authority by a name = {}", authority);
    authorityRepository.deleteUserAuthorityByAuthority(authority);
  }

  @Override
  public Long getUsersCountByRole(String authority) {

    log.info("Get number of users for role = {}", authority);
    List<User> users = userRepository.getUsersByRole_Authority(authority);

    return (long) users.size();
  }

  @Override
  public Page<UserAuthority> getAuthorities(Pageable pageable) {

    log.info("Get a page of all authorities");
    Page<UserAuthority> authorities = authorityRepository.findAll(pageable);

    return authorities;
  }
}

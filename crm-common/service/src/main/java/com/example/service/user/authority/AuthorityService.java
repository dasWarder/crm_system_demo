package com.example.service.user.authority;

import com.example.exception.AuthorityNotFoundException;
import com.example.model.user.UserAuthority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorityService {

  UserAuthority saveUserAuthority(UserAuthority authority);

  UserAuthority updateUserAuthorityByAuthorityName(final String authority, UserAuthority updateAuthority)
      throws AuthorityNotFoundException;

  UserAuthority getUserAuthorityByAuthorityName(final String authority) throws AuthorityNotFoundException;

  void deleteUserAuthorityByAuthorityName(final String authority);

  Long getUsersCountByRole(final String authority);

  Page<UserAuthority> getAuthorities(final Pageable pageable);
}

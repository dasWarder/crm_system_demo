package com.example.service.user.authority;

import com.example.exception.AuthorityNotFoundException;
import com.example.model.user.UserAuthority;

public interface AuthorityService {

  UserAuthority saveUserAuthority(UserAuthority authority);

  UserAuthority updateUserAuthorityByAuthorityName(String authority, UserAuthority updateAuthority)
      throws AuthorityNotFoundException;

  UserAuthority getUserAuthorityByAuthorityName(String authority) throws AuthorityNotFoundException;

  void deleteUserAuthorityByAuthorityName(String authority);
}

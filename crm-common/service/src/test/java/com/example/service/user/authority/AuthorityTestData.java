package com.example.service.user.authority;

import com.example.model.user.UserAuthority;

public class AuthorityTestData {

    public static final UserAuthority TEST_AUTHORITY_1 = new UserAuthority(1L, "USER");

    public static final UserAuthority TEST_AUTHORITY_2 = new UserAuthority(2L, "MANAGER");

    public static final UserAuthority TEST_AUTHORITY_3 = new UserAuthority(3L, "ADMIN");

    public static final UserAuthority TEST_SAVE_AUTHORITY = new UserAuthority(4L, "SUPER_ADMIN_ADMIN");

    public static final UserAuthority TEST_UPDATE_AUTHORITY = new UserAuthority(TEST_AUTHORITY_2.getId(), "LOW_MANAGER");
}

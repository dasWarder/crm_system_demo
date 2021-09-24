package com.example.web.data;

import com.example.model.user.UserAuthority;

public class TestAuthorityData {

    public static final UserAuthority TEST_AUTHORITY_1 = new UserAuthority(100000L, "USER");

    public static final UserAuthority TEST_AUTHORITY_2 = new UserAuthority(100001L, "ADMIN");

    public static final UserAuthority TEST_SAVE_AUTHORITY = new UserAuthority(100002L, "MANAGER");

    public static final UserAuthority TEST_UPDATE_AUTHORITY = new UserAuthority(TEST_AUTHORITY_2.getId(), "SUPER_ADMIN");
}

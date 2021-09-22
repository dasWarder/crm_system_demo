package com.example.service.user;

import com.example.model.user.User;
import com.example.model.user.UserAuthority;

import java.time.LocalDate;

public class UserTestData {

    public static final UserAuthority TEST_AUTHORITY = new UserAuthority(1L, "USER");

    public static final User TEST_USER_1 =
            User.builder()
                    .id(1L)
                    .email("test@gmail.com")
                    .password("12345")
                    .enabled(true)
                    .registrationDate(LocalDate.of(2020, 12, 12))
                    .role(TEST_AUTHORITY)
                    .build();

    public static final User TEST_USER_2 =
            User.builder()
                    .id(2L)
                    .email("test_2@gmail.com")
                    .password("12345")
                    .enabled(true)
                    .role(TEST_AUTHORITY)
                    .registrationDate(LocalDate.of(2020, 12, 13))
                    .build();

    public static final User TEST_SAVE_USER =
            User.builder()
                    .id(3L)
                    .email("test_3@gmail.com")
                    .enabled(true)
                    .role(TEST_AUTHORITY)
                    .registrationDate(LocalDate.of(2020, 11, 11))
                    .build();

    public static final User TEST_UPDATE_USER =
            User.builder()
                    .id(TEST_USER_2.getId())
                    .email("updated@gmail.com")
                    .enabled(true)
                    .role(TEST_AUTHORITY)
                    .registrationDate(LocalDate.of(2020, 11, 10))
                    .build();
}

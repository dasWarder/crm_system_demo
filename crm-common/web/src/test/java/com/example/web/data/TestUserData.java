package com.example.web.data;

import com.example.model.user.User;

public class TestUserData {

  public static final User TEST_USER_1 =
      User.builder().id(1L).email("test@gmail.com").password("12345").build();

  public static final User TEST_USER_2 =
      User.builder().id(2L).email("test2@gmail.com").password("12345").build();

  public static final User TEST_SAVE_USER =
      User.builder().id(3L).email("test3_gmail.com").password("12345").enabled(true).build();

  public static final User TEST_UPDATE_USER =
      User.builder()
          .id(TEST_USER_2.getId())
          .email("update@gmail.com")
          .password("12345")
          .enabled(true)
          .build();
}

package com.example.web.data;

import com.example.model.user.User;

public class TestUserData {

  public static final User TEST_USER_1 =
      User.builder().id(1L).email("test@gmail.com").password("12345").build();

  public static final User TEST_USER_2 =
      User.builder().id(2L).email("test2@gmail.com").password("12345").build();


  public static final User TEST_USER_3_WITH_CONTACTS =
          User.builder().id(3L).email("test3@gmail.com").password("12345").contact(TestContactData.TEST_CONTACT_1).build();

  public static final User TEST_SAVE_USER =
      User.builder().id(3L).email("test4@gmail.com").password("12345").enabled(true).build();

  public static final User TEST_UPDATE_USER =
      User.builder()
          .id(TEST_USER_2.getId())
          .email("update@gmail.com")
          .password("12345")
          .enabled(true)
          .build();
}

package com.example.mapper.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserDto {

  private String firstName;

  private String lastName;

  private String jobTitle;

  private String company;

  private String email;

  private String country;

  private String mobilePhone;

  private String password;
}

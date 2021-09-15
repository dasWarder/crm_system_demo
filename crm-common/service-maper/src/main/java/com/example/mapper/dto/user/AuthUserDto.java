package com.example.mapper.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDto {

  @NotBlank(message = "The field is mandatory")
  @Email(message = "The field must be a valid email")
  private String email;

  @NotBlank(message = "The field is mandatory")
  @Size(min = 5, message = "The field must contain at least 5 characters")
  private String password;
}

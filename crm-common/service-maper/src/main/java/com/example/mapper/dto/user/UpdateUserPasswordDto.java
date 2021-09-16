package com.example.mapper.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserPasswordDto {

  @NotBlank(message = "The field is mandatory")
  private String oldPassword;

  @NotBlank(message = "The field is mandatory")
  @Min(value = 5, message = "The min size of a password must be 5 character")
  private String password;
}

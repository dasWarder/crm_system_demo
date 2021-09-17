package com.example.mapper.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordDto {

  @NotBlank(message = "The field is mandatory")
  @Min(value = 5, message = "The field must contain at least 5 characters")
  private String password;

  @NotBlank(message = "The field is mandatory")
  @Min(value = 5, message = "The field must contain at least 5 characters")
  private String confirmPassword;

  @NotBlank(message = "The field is mandatory")
  private String token;
}

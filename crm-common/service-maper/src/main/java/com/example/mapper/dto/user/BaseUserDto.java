package com.example.mapper.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserDto {

  @NotBlank(message = "The field is mandatory")
  @Email(message = "The field must be a valid email")
  private String email;
}

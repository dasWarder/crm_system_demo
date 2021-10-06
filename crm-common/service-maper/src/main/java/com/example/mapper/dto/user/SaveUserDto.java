package com.example.mapper.dto.user;

import com.example.mapper.dto.user.admin.CreateUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserDto extends CreateUserDto {

  @NotBlank(message = "The field is mandatory")
  @Size(min = 5, message = "The field must contain at least 5 characters")
  private String password;
}

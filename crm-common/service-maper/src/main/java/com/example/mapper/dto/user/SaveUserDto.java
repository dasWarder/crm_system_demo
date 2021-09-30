package com.example.mapper.dto.user;

import com.example.mapper.dto.user.admin.CreateUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserDto extends CreateUserDto {

  public SaveUserDto(
      @NotBlank(message = "The field is mandatory") String firstName,
      @NotBlank(message = "The field is mandatory") String lastName,
      String jobTitle,
      String company,
      @NotBlank(message = "The field is mandatory")
          @Email(message = "The field must be a valid email")
          String email,
      String country,
      String mobilePhone,
      boolean enabled,
      @NotNull String role,
      String password) {
    super(firstName, lastName, jobTitle, company, email, country, mobilePhone, enabled, role);
    this.password = password;
  }

  @NotBlank(message = "The field is mandatory")
  @Size(min = 5, message = "The field must contain at least 5 characters")
  private String password;
}

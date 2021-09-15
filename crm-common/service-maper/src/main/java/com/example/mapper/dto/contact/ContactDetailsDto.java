package com.example.mapper.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetailsDto {

  @NotBlank(message = "The field is mandatory")
  private String firstName;

  @NotBlank(message = "The field is mandatory")
  private String lastName;

  private String jobTitle;

  private String company;

  private String country;

  @Email(message = "The field must be a valid email")
  @NotBlank(message = "The field is mandatory")
  private String email;

  private String mobilePhone;
}

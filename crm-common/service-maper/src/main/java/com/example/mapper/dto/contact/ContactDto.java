package com.example.mapper.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {

  private Long id;

  @NotBlank(message = "The field is mandatory")
  private String firstName;

  @NotBlank(message = "The field is mandatory")
  private String lastName;

  private String jobTitle;

  private String company;

}

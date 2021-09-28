package com.example.mapper.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveContactDto extends ContactDto {

  public SaveContactDto(
      Long id,
      @NotBlank(message = "The field is mandatory") String firstName,
      @NotBlank(message = "The field is mandatory") String lastName,
      String jobTitle,
      String company,
      String country,
      String mobilePhone) {
    super(id, firstName, lastName, jobTitle, company);
    this.country = country;
    this.mobilePhone = mobilePhone;
  }

  private String country;

  private String mobilePhone;
}

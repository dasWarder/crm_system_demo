package com.example.mapper.dto.user.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    @NotBlank(message = "The field is mandatory")
    private String firstName;

    @NotBlank(message = "The field is mandatory")
    private String lastName;

    private String jobTitle;

    private String company;

    @NotBlank(message = "The field is mandatory")
    @Email(message = "The field must be a valid email")
    private String email;

    private String country;

    private String mobilePhone;

    private boolean enabled;

    private String role;
}

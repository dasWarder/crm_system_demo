package com.example.mapper.dto.user.admin;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@SuperBuilder
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

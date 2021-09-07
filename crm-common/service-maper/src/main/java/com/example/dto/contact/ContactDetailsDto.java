package com.example.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetailsDto {

    private String firstName;

    private String lastName;

    private String jobTitle;

    private String company;

    private String country;

    private String email;

    private String mobilePhone;
}

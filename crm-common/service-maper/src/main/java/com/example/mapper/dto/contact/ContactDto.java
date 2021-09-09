package com.example.mapper.dto.contact;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String jobTitle;

    private String company;

    private String email;
}

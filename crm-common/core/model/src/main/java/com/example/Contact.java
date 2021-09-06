package com.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact")
public class Contact {

    @Id
    @SequenceGenerator(name = "contact_seq", sequenceName = "contact_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_seq")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "company")
    private String company;

    @Column(name = "country")
    private String country;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_phone")
    private String mobilePhone;
}

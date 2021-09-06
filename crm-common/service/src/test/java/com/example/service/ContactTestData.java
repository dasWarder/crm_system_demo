package com.example.service;

import com.example.Contact;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ContactTestData {

    public static final Pageable TEST_PAGEABLE = PageRequest.of(0, 10);

    public static final Contact TEST_CONTACT_1 = Contact.builder()
                                                                    .id(1L)
                                                                    .firstName("Alex")
                                                                    .lastName("Petrov")
                                                                    .jobTitle("Software Engineer")
                                                                    .email("alex_petrov@gmail.com")
                                                                    .company("Apple")
                                                                    .country("USA")
                                                                    .mobilePhone("+1375690543")
                                                                    .build();

    public static final Contact TEST_CONTACT_2 = Contact.builder()
                                                                    .id(2L)
                                                                    .firstName("Jack")
                                                                    .lastName("Smirnov")
                                                                    .jobTitle("CTO")
                                                                    .email("jack_smirnov@gmail.com")
                                                                    .company("Google")
                                                                    .country("UK")
                                                                    .mobilePhone("+4433333333")
                                                                    .build();

    public static final Contact TEST_CONTACT_3 = Contact.builder()
                                                                    .id(3L)
                                                                    .firstName("Mike")
                                                                    .lastName("Smith")
                                                                    .jobTitle("Architecture")
                                                                    .email("mike_smith@gmail.com")
                                                                    .company("Archer LDT")
                                                                    .country("Austria")
                                                                    .mobilePhone("+320555555")
                                                                    .build();

    public static final Contact TEST_UPDATE_CONTACT = Contact.builder()
                                                                    .id(null)
                                                                    .firstName("update")
                                                                    .lastName("Update")
                                                                    .jobTitle("Updater")
                                                                    .email("update@gmail.com")
                                                                    .company("Update LDT")
                                                                    .country("Austria")
                                                                    .mobilePhone("+320555555")
                                                                    .build();
}

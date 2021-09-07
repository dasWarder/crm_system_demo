package com.example.contactService.data;

import com.example.contactManager.Contact;

public class TestContactData {

    public static final Contact TEST_CONTACT_1 = Contact.builder()
                                                                .id(1L)
                                                                .firstName("Alex")
                                                                .lastName("Davidson")
                                                                .jobTitle("CEO")
                                                                .company("Harper Corporation")
                                                                .country("USA")
                                                                .email("alex_davidson@gmail.com")
                                                                .mobilePhone("+12027995223")
                                                                .build();

    public static final Contact TEST_CONTACT_2 = Contact.builder()
                                                                .id(2L)
                                                                .firstName("Jack")
                                                                .lastName("Smith")
                                                                .jobTitle("Architecture")
                                                                .company("Peterson & Davids")
                                                                .country("Canada")
                                                                .email("jack_smith@gmail.com")
                                                                .mobilePhone("+14378455569")
                                                                .build();

    public static final Contact TEST_CONTACT_3 = Contact.builder()
                                                                .id(3L)
                                                                .firstName("Maria")
                                                                .lastName("Smirnova")
                                                                .jobTitle("Lead Designer")
                                                                .company("ReversePyramid LTD")
                                                                .country("UK")
                                                                .email("maria_smirnova@gmail.com")
                                                                .mobilePhone("+447911123456")
                                                                .build();

    public static final Contact TEST_CONTACT_4 = Contact.builder()
                                                                .id(4L)
                                                                .firstName("Martin")
                                                                .lastName("Laanpere")
                                                                .jobTitle("Software Engineer")
                                                                .company("TLU")
                                                                .country("Estonia")
                                                                .email("martin_laanpere@gmail.com")
                                                                .mobilePhone("+3724444444")
                                                                .build();

    public static final Contact TEST_CONTACT_5 = Contact.builder()
                                                                .id(5L)
                                                                .firstName("Viktor")
                                                                .lastName("Volochay")
                                                                .jobTitle("Product manager")
                                                                .company("Wildmap")
                                                                .country("Ukraine")
                                                                .email("viktor_volochay@gmail.com")
                                                                .mobilePhone("+3805543321")
                                                                .build();

    public static final Contact TEST_SAVE_CONTACT = Contact.builder()
                                                                .id(6L)
                                                                .firstName("David")
                                                                .lastName("Blane")
                                                                .jobTitle("HR")
                                                                .company("Apple")
                                                                .country("USA")
                                                                .email("david_blane@gmail.com")
                                                                .mobilePhone("+133333333")
                                                                .build();

    public static final Contact TEST_UPDATE_CONTACT = Contact.builder()
                                                                .id(TEST_CONTACT_3.getId())
                                                                .firstName("Update")
                                                                .lastName("Update")
                                                                .jobTitle("Update")
                                                                .company("Apple")
                                                                .country("USA")
                                                                .email("update_update@gmail.com")
                                                                .mobilePhone("+133333333")
                                                                .build();


}

package com.example.web.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class TestData {

    public static final Pageable TEST_PAGEABLE = PageRequest.of(0, 10);

    public static final Long WRONG_ID = 1234567L;

    public static final String WRONG_EMAIL = "wrong@mail.ru";
}

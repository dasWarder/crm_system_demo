package com.example.service;

import com.example.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactSearchingService {

    Page<Contact> findAllByFullName(final String firstName, final String lastName, final Pageable pageable);
}

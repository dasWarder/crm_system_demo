package com.example.service;

import com.example.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactSearchingService {

    Page<Contact> findAllByParam(final String filteredBy, final String query, final Pageable pageable);
}

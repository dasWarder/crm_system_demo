package com.example.repository;

import com.example.model.contactManager.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ContactRepository
    extends PagingAndSortingRepository<Contact, Long>, JpaSpecificationExecutor<Contact> {

  @Override
  Page<Contact> findAll(Pageable pageable);

  Optional<Contact> getContactByEmail(String email);

  void deleteContactByEmail(String email);

  @Override
  Page<Contact> findAll(Specification<Contact> specification, Pageable pageable);
}

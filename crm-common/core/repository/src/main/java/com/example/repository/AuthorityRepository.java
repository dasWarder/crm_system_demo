package com.example.repository;

import com.example.model.user.UserAuthority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AuthorityRepository extends PagingAndSortingRepository<UserAuthority, Long> {

  Optional<UserAuthority> getUserAuthorityByAuthority(String authority);

  void deleteUserAuthorityByAuthority(String authority);

  @Override
  Page<UserAuthority> findAll(Pageable pageable);
}

package com.example.repository;

import com.example.model.user.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<UserAuthority, Long> {

  Optional<UserAuthority> getUserAuthorityByAuthority(String authority);

  void deleteUserAuthorityByAuthority(String authority);
}

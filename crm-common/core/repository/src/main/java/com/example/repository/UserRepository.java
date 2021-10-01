package com.example.repository;

import com.example.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {

  Optional<User> getUserByEmail(String email);

  @Override
  Page<User> findAll(Pageable pageable);

  void deleteUserByEmail(String email);

  Page<User> getUsersByRole_Authority(String role, Pageable pageable);

  @Override
  Page<User> findAll(Specification<User> specification, Pageable pageable);
}

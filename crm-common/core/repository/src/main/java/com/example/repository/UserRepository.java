package com.example.repository;

import com.example.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Optional<User> getUserByEmail(String email);

    @Override
    Page<User> findAll(Pageable pageable);

    void deleteUserByEmail(String email);
}

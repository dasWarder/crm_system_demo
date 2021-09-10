package com.example.service.user;

import com.example.exception.AuthorityNotFoundException;
import com.example.exception.UserAlreadyExistException;
import com.example.exception.UserNotFoundException;
import com.example.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

  User saveUser(User user) throws UserAlreadyExistException, AuthorityNotFoundException;

  User getUserByEmail(String email) throws UserNotFoundException;

  User updateUserByEmail(String email, User user) throws UserNotFoundException;

  void deleteUserByEmail(String email);

  Page<User> getUsers(Pageable pageable);
}

package com.example.service.user;

import com.example.exception.UserAlreadyExistException;
import com.example.exception.UserNotFoundException;
import com.example.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User saveUser(User user) throws UserAlreadyExistException;

    User getUserByEmail(String email) throws UserNotFoundException;

    User updateUserByEmail(String email, User user) throws UserNotFoundException;

    void deleteUserByEmail(String email);

    Page<User> getUsers(Pageable pageable);
}

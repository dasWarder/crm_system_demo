package com.example.service.user;

import com.example.exception.*;
import com.example.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

  User getCurrentUser() throws UserNotFoundException;

  User saveUser(User user) throws UserAlreadyExistException, AuthorityNotFoundException;

  User saveDefaultPasswordUser(User user, String role) throws UserAlreadyExistException, AuthorityNotFoundException;

  User getUserByEmail(String email) throws UserNotFoundException;

  User updateUserByEmail(String email, User user) throws UserNotFoundException;

  User updateUserPassByEmail(String email, String oldPass, String newPass) throws UserNotFoundException, WrongPasswordException;

  User updateUserEmail(String oldEmail, String email) throws UserNotFoundException;

  void deleteUserByEmail(String email);

  void deleteCommonUserByEmail(String email) throws UserNotFoundException, NotPossibleDeleteException;

  Page<User> getUsers(Pageable pageable);

  Page<User> getUsersByRole(String role, Pageable pageable) throws AuthorityNotFoundException;

  User updateUserRole(String email, String role) throws UserNotFoundException, AuthorityNotFoundException;
}

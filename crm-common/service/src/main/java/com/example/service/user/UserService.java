package com.example.service.user;

import com.example.exception.*;
import com.example.model.contactManager.Contact;
import com.example.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

  User getCurrentUser() throws UserNotFoundException;

  User saveUser(User user, Contact contact) throws UserAlreadyExistException, AuthorityNotFoundException;

  User saveDefaultPasswordUser(User user, String role, Contact contact) throws UserAlreadyExistException, AuthorityNotFoundException;

  User getUserByEmail(final String email) throws UserNotFoundException;

  User updateUserByEmail(final String email, User user) throws UserNotFoundException;

  User updateUserPassByEmail(final String email, final String oldPass, final String newPass) throws UserNotFoundException, WrongPasswordException;

  User updateUserEmail(final String oldEmail, final String email) throws UserNotFoundException;

  void deleteUserByEmail(final String email);

  void deleteCommonUserByEmail(final String email) throws UserNotFoundException, NotPossibleDeleteException;

  Page<User> getUsers(final Pageable pageable);

  Page<User> getUsersByRole(final String role, final Pageable pageable) throws AuthorityNotFoundException;

  User updateUserRole(final String email, final String role) throws UserNotFoundException, AuthorityNotFoundException;

  Page<User> getUsersByParam(final String param, final String query, final Pageable pageable) throws UnsupportedParameterException;
}

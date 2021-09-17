package com.example.service.user;

import com.example.exception.PasswordResetTokenNotFoundException;
import com.example.exception.ResetTokenExpiryException;
import com.example.exception.UserNotFoundException;
import com.example.exception.WrongPasswordException;
import com.example.model.user.User;

public interface ForgotPasswordService {

  void processForgotPassword(String email) throws UserNotFoundException;

  String directResetPassword(String token) throws PasswordResetTokenNotFoundException, ResetTokenExpiryException;

  User resetUserPassword(String password, String confirmPassword, String token) throws PasswordResetTokenNotFoundException, WrongPasswordException, UserNotFoundException;
}

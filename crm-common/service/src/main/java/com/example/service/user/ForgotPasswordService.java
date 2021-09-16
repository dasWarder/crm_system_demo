package com.example.service.user;

import com.example.exception.UserNotFoundException;
import com.example.model.user.User;

public interface ForgotPasswordService {

    void processForgotPassword(String email) throws UserNotFoundException;

    User resetPassword(String email, String oldPassword, String password);
}

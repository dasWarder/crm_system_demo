package com.example.service.security;

import com.example.exception.UserNotFoundException;
import com.example.service.user.UserService;
import com.example.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsSecurityService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User userByEmail = null;

        try {
            userByEmail = userService.getUserByEmail(email);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(
                    String.format("User with the email = %s not found"));
        }

        return new CustomUserDetails(userByEmail);
    }
}

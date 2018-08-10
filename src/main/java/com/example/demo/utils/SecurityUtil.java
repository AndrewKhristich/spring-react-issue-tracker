package com.example.demo.utils;

import com.example.demo.model.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    static public String getAuthorizedUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    static public boolean isAuthenticated() {
        return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    static public void unAuthenticate() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    static public User getUserFromSession() {
        if (!isAuthenticated()) {
            throw new AuthenticationServiceException("User is Anonymous");
        }
        return (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

}


package com.example.demo.utils;

import com.example.demo.model.User;
import com.example.demo.security.UserPrincipal;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    static public boolean isAuthEmpty(Authentication authentication) {
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return true;
    }

    static public Authentication getAuth() {
        return getAuthentication();
    }

    static public String getAuthorizedUsername() {
        return getAuthentication().getName();
    }

    static public boolean isAuthenticated() {
        return !(getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    static public void unAuthenticate() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    static public UserPrincipal getUserFromSession() {
        if (!isAuthenticated()) {
            throw new AuthenticationServiceException("User is Anonymous");
        }
        return (UserPrincipal) getAuthentication().getPrincipal();
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}


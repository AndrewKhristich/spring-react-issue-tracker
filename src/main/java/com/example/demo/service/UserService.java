package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService extends UserDetailsService {
    String authenticate(String username, String password);
    void saveUser(String username, String password);
    UserDetails loadUserById(Long userId);
}

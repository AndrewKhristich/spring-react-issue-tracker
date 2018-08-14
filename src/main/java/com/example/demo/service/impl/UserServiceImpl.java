package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.HibernateUtils;
import com.example.demo.utils.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository repo;
    private PasswordEncoder encoder;
    private AuthenticationManager authManager;

    @Autowired
    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder, AuthenticationManager authManager) {
        this.repo = repo;
        this.encoder = encoder;
        this.authManager = authManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User userByName = repo.findByUsername(username).get();
            HibernateUtils.initialize(userByName.getAuthorities());
            return userByName;
        } catch (UsernameNotFoundException e){
            LOG.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void saveUser(String username, String password) {
        repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with name '" + username + "' already exists"));
        String encodedPass = encoder.encode(password);
        repo.save(new User(username, encodedPass));
    }

    @Override
    public UserDetails loadUserById(Long userId) {
        User user = repo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        return user;
    }
}

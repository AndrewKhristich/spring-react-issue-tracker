package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
}

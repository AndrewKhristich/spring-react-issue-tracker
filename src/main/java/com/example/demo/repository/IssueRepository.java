package com.example.demo.repository;

import com.example.demo.model.Issue;
import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends PagingAndSortingRepository<Issue, Long> {
    Page<Issue> findByUser(User user, Pageable pageRequest);
}

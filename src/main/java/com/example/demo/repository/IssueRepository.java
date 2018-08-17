package com.example.demo.repository;

import com.example.demo.model.Issue;
import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends PagingAndSortingRepository<Issue, Long> {
    @Query(nativeQuery = true, value = "select * from issues iss where iss.author_id = ?1")
    Page<Issue> findByUser(Long userId, Pageable pageRequest);
}

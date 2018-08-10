package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.model.Issue;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.util.List;

public interface IssueService {
    Page<Issue> findAll(Integer pageNum, Integer pageSize);

    Issue findById(Long parameter);

    void saveIssue(@Valid Issue issue);

    Page<Issue> findAllOfCurrentUser(Integer pageNum, Integer pageSize);

    List<Comment> findCommentsByIssueId(Long id);

    void saveCommentToIssue(Long issueId, Comment comment);
}

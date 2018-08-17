package com.example.demo.service.impl;

import com.example.demo.model.Comment;
import com.example.demo.model.Issue;
import com.example.demo.model.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.IssueRepository;
import com.example.demo.security.UserPrincipal;
import com.example.demo.service.IssueService;
import com.example.demo.utils.HibernateUtils;
import com.example.demo.utils.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private IssueRepository repository;
    private CommentRepository commentRepository;

    public IssueServiceImpl(IssueRepository repository, CommentRepository commentRepository) {
        this.repository = repository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Page<Issue> findAll(Integer pageNum, Integer pageSize) {
        return repository.findAll(PageRequest.of(pageNum, pageSize));
    }

    @Override
    public Issue findById(Long parameter) {
        Issue issue = repository.findById(parameter).get();
        HibernateUtils.initialize(issue.getComments());
        return issue;
    }

    @Override
    public void saveIssue(@Valid Issue issue) {
//        User userFromSession = SecurityUtil.getUserFromSession();
//        issue.setUser(userFromSession);
        repository.save(issue);
    }

    @Override
    public Page<Issue> findAllOfCurrentUser(Integer pageNum, Integer pageSize) {
        UserPrincipal userFromSession = SecurityUtil.getUserFromSession();
        return repository.findByUser(userFromSession.getId(), PageRequest.of(pageNum, pageSize));
    }

    @Override
    public List<Comment> findCommentsByIssueId(Long id) {
        List<Comment> comments = commentRepository.findAllByIssueId(id);
        return comments;
    }

    @Override
    public void saveCommentToIssue(Long issueId, Comment comment) {
        Issue issue = repository.findById(issueId).get();
        comment.setIssue(issue);
        comment.setAuthor(SecurityUtil.getAuthorizedUsername());
        String status = comment.getStatus();
        if (status!=null && status.equals("Resolved")) {
            issue.setStatus("Resolved");
            repository.save(issue);
        }
        commentRepository.save(comment);
    }
}

package com.example.demo.controllers;

import com.example.demo.dto.CommentDTO;
import com.example.demo.model.Comment;
import com.example.demo.service.IssueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentRestController {

    private IssueService issueService;

    public CommentRestController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/issue/{id}")
    public List<Comment> findAllCommentsByIssue(@PathVariable Long id) {
        return issueService.findCommentsByIssueId(id);
    }

    @PostMapping
    public void saveComment(@RequestBody CommentDTO dto) {
        issueService.saveCommentToIssue(dto.getIssueId(), new Comment(dto.getDescription(), dto.getStatus()));
    }
}

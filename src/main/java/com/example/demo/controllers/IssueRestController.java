package com.example.demo.controllers;

import com.example.demo.model.Issue;
import com.example.demo.service.IssueService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/issues")
@CrossOrigin(origins = "*") // TODO change me
public class IssueRestController {

    private IssueService issueService;

    public IssueRestController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public Page<Issue> findAllIssues(@RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "20") Integer pageSize) {
        return issueService.findAll(pageNum, pageSize);
    }

    @GetMapping(value = "/myissues", params = {"pageNum", "pageSize"})
    public Page<Issue> findIssuesOfCurrentUser(@RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                               @RequestParam(required = false, defaultValue = "20") Integer pageSize) {
        return issueService.findAllOfCurrentUser(pageNum, pageSize);
    }

    @GetMapping(value = "/{id}")
    public Issue findIssueById(@PathVariable(required = true) Long id) {
        return issueService.findById(id);
    }
}

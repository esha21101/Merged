package com.merged.merged.controller;

import com.merged.merged.model.Issue;
import com.merged.merged.service.GitHubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.merged.merged.model.ClaimedIssue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
public class IssueController {

    private final GitHubService gitHubService;

    public IssueController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/api/issues")
    public List<Issue> getIssues(@RequestParam String language) {
        return gitHubService.findGoodFirstIssues(language);
    }

    @PostMapping("/api/claim")
public ClaimedIssue claimIssue(@RequestBody Issue issue) {
    return gitHubService.claimIssue(issue);
}
}
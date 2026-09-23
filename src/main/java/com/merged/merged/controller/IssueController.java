package com.merged.merged.controller;

import com.merged.merged.model.Issue;
import com.merged.merged.service.GitHubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.merged.merged.model.ClaimedIssue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import com.merged.merged.model.PrSubmission;


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

    @GetMapping("/api/claimed")
public List<ClaimedIssue> getAllClaimedIssues() {
    return gitHubService.getAllClaimedIssues();
}

    @PutMapping("/api/claimed/{id}/submit-pr")
public ClaimedIssue submitPrLink(@PathVariable Long id, @RequestBody PrSubmission submission) {
    return gitHubService.submitPrLink(id, submission.getPrLink());
}
}
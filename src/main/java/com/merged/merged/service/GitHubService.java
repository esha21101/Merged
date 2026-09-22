package com.merged.merged.service;

import com.merged.merged.model.GitHubSearchResponse;
import com.merged.merged.model.Issue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GitHubService {

    @Value("${github.token}")
    private String githubToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Issue> findGoodFirstIssues(String language) {
        String url = "https://api.github.com/search/issues?q=label:good-first-issue+language:"
                + language + "+state:open";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + githubToken);
        headers.set("Accept", "application/vnd.github+json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<GitHubSearchResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                GitHubSearchResponse.class
        );

        return response.getBody().getItems();
    }
}
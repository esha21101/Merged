package com.merged.merged.repository;

import com.merged.merged.model.ClaimedIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimedIssueRepository extends JpaRepository<ClaimedIssue, Long> {
}
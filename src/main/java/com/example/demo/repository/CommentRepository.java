package com.example.demo.repository;

import com.example.demo.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    @Query(value = "select * from issue_comments where issue_id = ?1", nativeQuery = true)
    List<Comment> findAllByIssueId(Long issueId);
}

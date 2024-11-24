package com.example.taskManager.repository;

import com.example.taskManager.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTaskId(Long taskId);

    Page<Comment> findByTaskId(Long taskId, Pageable pageable);
}

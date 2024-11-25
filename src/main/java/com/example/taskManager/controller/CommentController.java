package com.example.taskManager.controller;

import com.example.taskManager.model.Comment;
import com.example.taskManager.model.User;
import com.example.taskManager.service.CommentService;
import com.example.taskManager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tasks/{taskId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Comment> addComment(@PathVariable Long taskId, @RequestBody Comment comment, Principal principal) {
        User currentUser = userService.getCurrentUser(); // Получение текущего пользователя
        Comment createdComment = commentService.addComment(taskId, comment.getContent(), currentUser);
        return ResponseEntity.ok(createdComment);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long taskId) {
        return ResponseEntity.ok(commentService.getCommentsByTask(taskId));
    }
}


package com.example.taskManager.controller;

import com.example.taskManager.model.Task;
import com.example.taskManager.model.Task.TaskStatus;
import com.example.taskManager.model.User;
import com.example.taskManager.service.TaskService;
import com.example.taskManager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping("/my-tasks")
    public ResponseEntity<List<Task>> getTasksByAssignee() {
        User currentUser = userService.getCurrentUser();
        return ResponseEntity.ok(taskService.getTasksByAssignee(currentUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(id, updatedTask);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully!");
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id, @RequestBody TaskStatus status) {
        Task updatedTask = taskService.updateStatus(id, status);
        return ResponseEntity.ok(updatedTask);
    }

    @GetMapping
    public ResponseEntity<Page<Task>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(taskService.getTasksWithPagination(page, size, sortBy));
    }
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Task>> getTasksByAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(taskService.getTasksByAuthor(authorId));
    }

}

package com.example.taskManager;

import com.example.taskManager.model.Task;
import com.example.taskManager.repository.TaskRepository;
import com.example.taskManager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
class TaskServiceTest {

    private TaskRepository taskRepository = Mockito.mock(TaskRepository.class);
    private TaskService taskService = new TaskService(taskRepository);

    @Test
    void testCreateTask() {
        Task task = new Task();
        task.setTitle("New Task");

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task createdTask = taskService.createTask(task);
        assertThat(createdTask.getTitle()).isEqualTo("New Task");
    }
}



package com.satitulasi.taskmanagementsystem.service;

import com.satitulasi.taskmanagementsystem.dto.TaskRequest;
import com.satitulasi.taskmanagementsystem.dto.TaskResponse;
import com.satitulasi.taskmanagementsystem.entity.Task;
import com.satitulasi.taskmanagementsystem.entity.TaskStatus;
import com.satitulasi.taskmanagementsystem.entity.User;
import com.satitulasi.taskmanagementsystem.exception.ResourceNotFoundException;
import com.satitulasi.taskmanagementsystem.repository.TaskRepository;
import com.satitulasi.taskmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskResponse createTask(TaskRequest request) {

        String email = getAuthenticatedEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus() != null
                        ? request.getStatus()
                        : TaskStatus.TODO)
                .dueDate(request.getDueDate())
                .user(user)
                .build();

        Task savedTask = taskRepository.save(task);

        return mapToResponse(savedTask);
    }

    public List<TaskResponse> getAllTasks() {

        User user = getAuthenticatedUser();

        return taskRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public TaskResponse getTaskById(Long taskId) {

        User user = getAuthenticatedUser();

        Task task = taskRepository.findByIdAndUserId(
                        taskId, user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        return mapToResponse(task);
    }

    public TaskResponse updateTask(Long taskId, TaskRequest request) {

        User user = getAuthenticatedUser();

        Task task = taskRepository.findByIdAndUserId(
                        taskId, user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());

        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }

        Task updatedTask = taskRepository.save(task);

        return mapToResponse(updatedTask);
    }

    public void deleteTask(Long taskId) {

        User user = getAuthenticatedUser();

        Task task = taskRepository.findByIdAndUserId(
                        taskId, user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        taskRepository.delete(task);
    }

    public TaskResponse updateTaskStatus(
            Long taskId, TaskStatus status) {

        User user = getAuthenticatedUser();

        Task task = taskRepository.findByIdAndUserId(
                        taskId, user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        task.setStatus(status);

        return mapToResponse(taskRepository.save(task));
    }

    private User getAuthenticatedUser() {

        String email = getAuthenticatedEmail();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    private String getAuthenticatedEmail() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        return authentication.getName();
    }

    private TaskResponse mapToResponse(Task task) {

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .dueDate(task.getDueDate())
                .userId(task.getUser().getId())
                .build();
    }
}

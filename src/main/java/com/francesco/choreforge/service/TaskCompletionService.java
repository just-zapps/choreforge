package com.francesco.choreforge.service;

import com.francesco.choreforge.model.TaskStatus;
import com.francesco.choreforge.repository.PlayerJpaRepository;
import com.francesco.choreforge.repository.TaskInstanceJpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskCompletionService {

    private final TaskInstanceJpaRepository taskInstanceJpaRepository;
    private final PlayerJpaRepository playerJpaRepository;

    public TaskCompletionService(TaskInstanceJpaRepository taskInstanceJpaRepository,
                                 PlayerJpaRepository playerJpaRepository) {
        this.taskInstanceJpaRepository = taskInstanceJpaRepository;
        this.playerJpaRepository = playerJpaRepository;
    }

    public String completeTask(Long taskId) {
        return taskInstanceJpaRepository.findById(taskId)
                .map(task -> {
                    if (task.getStatus() == TaskStatus.COMPLETED) {
                        return "Task " + taskId + " is already completed";
                    }

                    if (LocalDateTime.now().isAfter(task.getDueAt())) {
                        return "Task " + taskId + " is overdue and cannot be completed";
                    }

                    task.setStatus(TaskStatus.COMPLETED);
                    task.setCompletedAt(LocalDateTime.now());
                    task.getAssignedTo().modifyScore(task.getTaskTemplate().getPoints());

                    playerJpaRepository.save(task.getAssignedTo());
                    taskInstanceJpaRepository.save(task);

                    return "Task " + taskId + " completed";
                })
                .orElse("Task not found");
    }
}
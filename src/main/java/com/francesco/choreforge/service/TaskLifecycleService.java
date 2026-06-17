package com.francesco.choreforge.service;

import com.francesco.choreforge.model.TaskStatus;
import com.francesco.choreforge.repository.PlayerJpaRepository;
import com.francesco.choreforge.repository.TaskInstanceJpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskLifecycleService {

    private final TaskInstanceJpaRepository taskInstanceJpaRepository;
    private final PlayerJpaRepository playerJpaRepository;

    public TaskLifecycleService(TaskInstanceJpaRepository taskInstanceJpaRepository,
                                PlayerJpaRepository playerJpaRepository) {
        this.taskInstanceJpaRepository = taskInstanceJpaRepository;
        this.playerJpaRepository = playerJpaRepository;
    }

    public void markExpiredTasksAsMissed() {
        LocalDateTime now = LocalDateTime.now();

        taskInstanceJpaRepository.findAll().forEach(task -> {
            if (task.getStatus() == TaskStatus.PENDING && now.isAfter(task.getDueAt())) {
                task.setStatus(TaskStatus.MISSED);

                if (!task.isPenaltyApplied()) {
                    task.getAssignedTo().modifyScore(-task.getTaskTemplate().getPenalty());
                    task.setPenaltyApplied(true);
                    playerJpaRepository.save(task.getAssignedTo());
                }

                taskInstanceJpaRepository.save(task);
            }
        });
    }
}
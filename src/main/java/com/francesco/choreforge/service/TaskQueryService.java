package com.francesco.choreforge.service;

import com.francesco.choreforge.model.TaskInstance;
import com.francesco.choreforge.repository.TaskInstanceJpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskQueryService {

    private final TaskInstanceJpaRepository taskInstanceJpaRepository;
    private final TaskLifecycleService taskLifecycleService;

    public TaskQueryService(TaskInstanceJpaRepository taskInstanceJpaRepository,
                            TaskLifecycleService taskLifecycleService) {
        this.taskInstanceJpaRepository = taskInstanceJpaRepository;
        this.taskLifecycleService = taskLifecycleService;
    }

    public List<TaskInstance> findAll() {
        taskLifecycleService.markExpiredTasksAsMissed();
        return taskInstanceJpaRepository.findAll();
    }

    public List<TaskInstance> findByDate(LocalDate date) {
        taskLifecycleService.markExpiredTasksAsMissed();
        return taskInstanceJpaRepository.findByDate(date);
    }

    public List<TaskInstance> findByPlayerId(Long playerId) {
        taskLifecycleService.markExpiredTasksAsMissed();
        return taskInstanceJpaRepository.findByAssignedTo_Id(playerId);
    }
}
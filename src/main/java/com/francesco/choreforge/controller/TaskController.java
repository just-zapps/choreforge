package com.francesco.choreforge.controller;

import com.francesco.choreforge.model.Player;
import com.francesco.choreforge.model.TaskInstance;
import com.francesco.choreforge.model.TaskStatus;
import com.francesco.choreforge.repository.PlayerJpaRepository;
import com.francesco.choreforge.repository.TaskInstanceJpaRepository;
import com.francesco.choreforge.service.GenerationService;
import com.francesco.choreforge.service.TaskLifecycleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TaskController {

    private final GenerationService generationService;
    private final PlayerJpaRepository playerJPARepository;
    private final TaskInstanceJpaRepository taskInstanceJpaRepository;
    private final TaskLifecycleService taskLifecycleService;

    public TaskController(GenerationService generationService, PlayerJpaRepository playerJPARepository, TaskInstanceJpaRepository taskInstanceJpaRepository, TaskLifecycleService taskLifecycleService) {
        this.generationService = generationService;
        this.playerJPARepository = playerJPARepository;
        this.taskInstanceJpaRepository = taskInstanceJpaRepository;
        this.taskLifecycleService = taskLifecycleService;
    }

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @GetMapping("/tasks/week")
    public List<TaskInstance> getWeeklyTasks() {
        return generationService.generateWeek(LocalDate.now());
    }

    @GetMapping("/tasks")
    public List<TaskInstance> getAllTasks() {
        return taskInstanceJpaRepository.findAll();
    }

    @PostMapping("/tasks/{id}/complete")
    public String completeTask(@PathVariable Long id) {
        return taskInstanceJpaRepository.findById(id)
                .map(task -> {
                    if (task.getStatus() == TaskStatus.COMPLETED) {
                        return "Task " + id + " is already completed!\n";
                    }
                    if (java.time.LocalDateTime.now().isAfter((task.getDueAt()))) {
                        return "Task " + id + " is overdue and cannot be completed!\n";
                    }
                    task.setStatus(TaskStatus.COMPLETED);
                    task.setCompletedAt(java.time.LocalDateTime.now());
                    task.getAssignedTo().modifyScore(task.getTaskTemplate().getPoints());
                    playerJPARepository.save(task.getAssignedTo());
                    return "Task " + id + " completed!\n";
                })
                .orElse("Task not found\n");
    }

    @GetMapping("/tasks/today")
    public List<TaskInstance> getTodayTasks() {
        taskLifecycleService.markExpiredTasksAsMissed();
        return taskInstanceJpaRepository.findByDate(LocalDate.now());
    }

    @GetMapping("/tasks/player/{id}")
    public List<TaskInstance> getPlayerTasks(@PathVariable Long id) {
        taskLifecycleService.markExpiredTasksAsMissed();
        return taskInstanceJpaRepository.findByAssignedTo_Id(id);
    }

    @GetMapping("/players")
    public List<Player> getPlayers() {
        return playerJPARepository.findAll();
    }

}

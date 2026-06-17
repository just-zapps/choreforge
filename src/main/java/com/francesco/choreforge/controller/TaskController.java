package com.francesco.choreforge.controller;

import com.francesco.choreforge.model.Player;
import com.francesco.choreforge.model.TaskInstance;
import com.francesco.choreforge.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TaskController {

    private final GenerationService generationService;
    private final TaskCompletionService taskCompletionService;
    private final TaskQueryService taskQueryService;
    private final PlayerQueryService playerQueryService;

    public TaskController(GenerationService generationService, TaskCompletionService taskCompletionService, TaskQueryService taskQueryService, PlayerQueryService playerQueryService) {
        this.generationService = generationService;
        this.taskCompletionService = taskCompletionService;
        this.taskQueryService = taskQueryService;
        this.playerQueryService = playerQueryService;
    }

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @GetMapping("/tasks/week")
    public List<TaskInstance> getWeeklyTasks() {
        return generationService.generateWeek(LocalDate.now());
    }

    @GetMapping("/tasks/week/{date}")
    public List<TaskInstance> getWeekTasksByDate(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {
        return generationService.generateWeek(date);
    }

    @GetMapping("/tasks")
    public List<TaskInstance> getAllTasks() {
        return taskQueryService.findAll();
    }

    @GetMapping("/tasks/today")
    public List<TaskInstance> getTodayTasks() {
        return taskQueryService.findByDate(LocalDate.now());
    }

    @GetMapping("/tasks/date/{date}")
    public List<TaskInstance> getTasksByDate(@PathVariable LocalDate date) {
        return taskQueryService.findByDate(date);
    }

    @GetMapping("/tasks/player/{id}")
    public List<TaskInstance> getTasksByPlayer(@PathVariable Long id) {
        return taskQueryService.findByPlayerId(id);
    }

    @PostMapping("/tasks/{id}/complete")
    public String completeTask(@PathVariable Long id) {
        return taskCompletionService.completeTask(id);
    }

    @GetMapping("/players")
    public List<Player> getPlayers() {
        return playerQueryService.findAll();
    }

}

package com.francesco.choreforge.controller;

import com.francesco.choreforge.model.TaskInstance;
import com.francesco.choreforge.model.TaskStatus;
import com.francesco.choreforge.repository.TaskRepository;
import com.francesco.choreforge.service.GenerationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TaskController {

    private final GenerationService generationService;
    private final TaskRepository taskRepository;

    public TaskController(GenerationService generationService, TaskRepository taskRepository) {
        this.generationService = generationService;
        this.taskRepository = taskRepository;
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
        return taskRepository.findAll();
    }

    @PostMapping("/tasks/{id}/complete")
    public String completeTask(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setStatus(TaskStatus.COMPLETED);
                    return "Task " + id + " completed!";
                })
                .orElse("Task not found");
    }

}

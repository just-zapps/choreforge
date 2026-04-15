package com.francesco.choreforge.controller;

import com.francesco.choreforge.model.TaskInstance;
import com.francesco.choreforge.service.GenerationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TaskController {

    private final GenerationService generationService;

    public TaskController(GenerationService generationService) {
        this.generationService = generationService;
    }

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @GetMapping("/tasks/week")
    public List<TaskInstance> getWeeklyTasks() {
        return generationService.generateWeek(LocalDate.now());
    }

}

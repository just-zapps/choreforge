package com.francesco.choreforge.controller;

import com.francesco.choreforge.model.TaskInstance;
import com.francesco.choreforge.service.GenerationService;
import com.francesco.choreforge.service.PlayerQueryService;
import com.francesco.choreforge.service.TaskCompletionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import java.time.LocalDate;
import java.util.List;

@Controller
public class WebController {

    private final GenerationService generationService;
    private final PlayerQueryService playerQueryService;
    private final TaskCompletionService taskCompletionService;

    public WebController(GenerationService generationService,
                         PlayerQueryService playerQueryService,
                         TaskCompletionService taskCompletionService) {
        this.generationService = generationService;
        this.playerQueryService = playerQueryService;
        this.taskCompletionService = taskCompletionService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<TaskInstance> weekTasks = generationService.generateWeek(LocalDate.now());

        Map<LocalDate, List<TaskInstance>> tasksByDate = weekTasks.stream()
                .sorted(
                        Comparator.comparing(TaskInstance::getDate)
                                .thenComparing(task -> task.getTaskTemplate().getName())
                )
                .collect(Collectors.groupingBy(
                        TaskInstance::getDate,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));

        model.addAttribute("tasksByDate", tasksByDate);
        model.addAttribute("players", playerQueryService.findAll());
        model.addAttribute("today", LocalDate.now());

        return "dashboard";
    }

    @PostMapping("/web/tasks/{id}/complete")
    public String completeTask(@PathVariable Long id) {
        taskCompletionService.completeTask(id);
        return "redirect:/dashboard";
    }
}
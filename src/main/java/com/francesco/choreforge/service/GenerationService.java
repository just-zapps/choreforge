package com.francesco.choreforge.service;

import com.francesco.choreforge.model.*;
import com.francesco.choreforge.config.DemoScheduleProvider;
import com.francesco.choreforge.repository.PlayerJpaRepository;
import com.francesco.choreforge.repository.TaskInstanceJpaRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenerationService {

    private final DemoScheduleProvider demoScheduleProvider;
    private final AssignmentService assignmentService;
    private final PlayerJpaRepository playerJPARepository;
    private final TaskInstanceJpaRepository taskInstanceJpaRepository;
    private final TaskLifecycleService taskLifecycleService;

    public GenerationService(DemoScheduleProvider demoScheduleProvider, AssignmentService assignmentService, PlayerJpaRepository playerJPARepository, TaskInstanceJpaRepository taskInstanceJpaRepository, TaskLifecycleService taskLifecycleService) {
        this.demoScheduleProvider = demoScheduleProvider;
        this.assignmentService = assignmentService;
        this.playerJPARepository = playerJPARepository;
        this.taskInstanceJpaRepository = taskInstanceJpaRepository;
        this.taskLifecycleService = taskLifecycleService;
    }

    public List<TaskInstance> generateWeek(LocalDate startDate) {
        LocalDate weekStart = startDate.with(
                java.time.temporal.TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)
        );

        LocalDate weekEnd = weekStart.plusDays(6);
        taskLifecycleService.markExpiredTasksAsMissed();
        List<TaskInstance> existingTasks =
                taskInstanceJpaRepository.findByDateBetween(weekStart, weekEnd);

        if (!existingTasks.isEmpty()) {
            return taskInstanceJpaRepository.findByDateBetween(weekStart, weekEnd);
        }

        List<Player> players = playerJPARepository.findAll();
        List<ScheduleRule> rules = demoScheduleProvider.getScheduleRules();

        List<TaskInstance> result = new ArrayList<>();
        int playerIndex = 0;

        for (int i = 0; i < 7; i++) {
            LocalDate date = weekStart.plusDays(i);
            for (ScheduleRule rule : rules) {
                if (rule.isEveryDay() || rule.getDayOfWeek() == date.getDayOfWeek()) {
                    if (rule.isGroupRule()) {
                        Player assigned = assignmentService.assignNextPlayer(players, playerIndex);
                        playerIndex++;

                        for (TaskGroupItem item : rule.getGroup().getItems()) {
                            result.add(new TaskInstance(
                                    item.getTaskTemplate(),
                                    date,
                                    assigned,
                                    rule.getGroup().getName()
                            ));
                        }
                    } else if (rule.isTaskRule()) {
                        Player assigned = assignmentService.assignNextPlayer(players, playerIndex);
                        playerIndex++;

                        result.add(new TaskInstance(
                                rule.getTaskTemplate(),
                                date,
                                assigned,
                                null
                        ));
                    }
                }
            }
        }
        taskInstanceJpaRepository.saveAll(result);
        return taskInstanceJpaRepository.findByDateBetween(weekStart, weekEnd);
    }
}

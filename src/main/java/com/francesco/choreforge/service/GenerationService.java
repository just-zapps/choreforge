package com.francesco.choreforge.service;

import com.francesco.choreforge.model.*;
import com.francesco.choreforge.repository.DemoDataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Service
public class GenerationService {

    private final DemoDataRepository demoDataRepository;
    private final AssignmentService assignmentService;

    public GenerationService(DemoDataRepository demoDataRepository, AssignmentService assignmentService) {
        this.demoDataRepository = demoDataRepository;
        this.assignmentService = assignmentService;
    }

    public List<TaskInstance> generateWeek(LocalDate startDate) {

        List<Player> players = demoDataRepository.getPlayers();
        List<ScheduleRule> rules = demoDataRepository.getScheduleRules();

        List<TaskInstance> result = new ArrayList<>();
        int playerIndex = 0;

        for (int i = 0; i < 7; i++) {
            LocalDate date = startDate.plusDays(i);
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
        return result;
    }
}

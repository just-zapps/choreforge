package com.francesco.choreforge.service;

import com.francesco.choreforge.model.*;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Service
public class GenerationService {

    public List<TaskInstance> generateWeek(LocalDate startDate) {

        List<Player> players = List.of(
                new Player(1L, "Player1"),
                new Player(2L, "Player2")
        );

        TaskTemplate cleanToilet = new TaskTemplate(1L, "Clean toilet", 10, 5);
        TaskTemplate cleanSink = new TaskTemplate(2L, "Clean sink", 8, 4);
        TaskTemplate emptyBin = new TaskTemplate(3L, "Empty bin", 5, 2);
        TaskTemplate tidyDesk = new TaskTemplate(4L, "Tidy desk", 6, 3);
        TaskTemplate takeOutTrash = new TaskTemplate(5L, "Take out trash", 4, 2);


        List<TaskGroupItem> bathroomItems = List.of(
          new TaskGroupItem(cleanToilet, true),
          new TaskGroupItem(cleanSink, true),
          new TaskGroupItem(emptyBin, true)
        );

        List<TaskGroupItem> studyItems = List.of(
                new TaskGroupItem(tidyDesk, true)
        );

        TaskGroupTemplate bathroomGroup = new TaskGroupTemplate(1L, "Bathroom", bathroomItems);
        TaskGroupTemplate studyGroup = new TaskGroupTemplate(2L, "Study", studyItems);

        List<ScheduleRule> rules = List.of(
                new ScheduleRule(DayOfWeek.TUESDAY, bathroomGroup),
                new ScheduleRule(DayOfWeek.WEDNESDAY, studyGroup),
                new ScheduleRule(DayOfWeek.TUESDAY, takeOutTrash)
        );

        List<TaskInstance> result = new ArrayList<>();

        int playerIndex = 0;

        for (int i = 0; i < 7; i++) {
            LocalDate date = startDate.plusDays(i);
            for (ScheduleRule rule : rules) {
                if (rule.getDayOfWeek() == date.getDayOfWeek()) {
                    Player assigned = players.get(playerIndex % players.size());
                    playerIndex++;

                    if (rule.isGroupRule()) {
                        for (TaskGroupItem item : rule.getGroup().getItems()) {
                            result.add(new TaskInstance(
                                    item.getTaskTemplate(),
                                    date,
                                    assigned
                            ));
                        }
                    } else if (rule.isTaskRule()) {
                        result.add(new TaskInstance(
                                rule.getTaskTemplate(),
                                date,
                                assigned
                        ));
                    }
                }
            }
        }
        return result;
    }
}

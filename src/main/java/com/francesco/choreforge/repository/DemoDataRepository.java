package com.francesco.choreforge.repository;

import com.francesco.choreforge.model.*;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public class DemoDataRepository {

    public List<Player> getPlayers() {
        return List.of(
                new Player(1L, "Player1"),
                new Player(2L, "Player2")
        );
    }

    public List<ScheduleRule> getScheduleRules() {

        TaskTemplate cleanToilet = new TaskTemplate(1L, "Clean toilet", 10, 5);
        TaskTemplate cleanSink = new TaskTemplate(2L, "Clean sink", 8, 4);
        TaskTemplate emptyBin = new TaskTemplate(3L, "Empty bin", 5, 2);
        TaskTemplate tidyDesk = new TaskTemplate(4L, "Tidy desk", 6, 3);
        TaskTemplate takeOutTrash = new TaskTemplate(5L, "Take out trash", 4, 2);
        TaskTemplate washDinnerDishes = new TaskTemplate(6L, "Wash dinner dishes", 5, 2);
        TaskTemplate cookDinner = new TaskTemplate(7L, "Cook dinner", 7, 3);

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


        return List.of(
                new ScheduleRule(DayOfWeek.TUESDAY, bathroomGroup),
                new ScheduleRule(DayOfWeek.WEDNESDAY, studyGroup),
                new ScheduleRule(DayOfWeek.TUESDAY, takeOutTrash),
                new ScheduleRule(washDinnerDishes),
                new ScheduleRule(cookDinner)
        );
    }

}

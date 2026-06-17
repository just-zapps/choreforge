package com.francesco.choreforge.config;

import com.francesco.choreforge.model.*;
import com.francesco.choreforge.repository.TaskTemplateJpaRepository;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.List;

@Component
public class DemoScheduleProvider {

    private final TaskTemplateJpaRepository taskTemplateJpaRepository;

    public DemoScheduleProvider(TaskTemplateJpaRepository taskTemplateJpaRepository) {
        this.taskTemplateJpaRepository = taskTemplateJpaRepository;
    }

    public List<ScheduleRule> getScheduleRules() {
        TaskTemplate cleanToilet = taskTemplateJpaRepository.findById(1L).orElseThrow();
        TaskTemplate cleanSink = taskTemplateJpaRepository.findById(2L).orElseThrow();
        TaskTemplate emptyBin = taskTemplateJpaRepository.findById(3L).orElseThrow();
        TaskTemplate tidyDesk = taskTemplateJpaRepository.findById(4L).orElseThrow();
        TaskTemplate takeOutTrash = taskTemplateJpaRepository.findById(5L).orElseThrow();
        TaskTemplate washDishes = taskTemplateJpaRepository.findById(6L).orElseThrow();

        List<TaskGroupItem> bathroomItems = List.of(
                new TaskGroupItem(cleanToilet, true),
                new TaskGroupItem(cleanSink, true),
                new TaskGroupItem(emptyBin, true)
        );

        List<TaskGroupItem> studyItems = List.of(
                new TaskGroupItem(tidyDesk, true)
        );

        TaskGroupTemplate bathroomGroup =
                new TaskGroupTemplate(1L, "Bathroom", bathroomItems);

        TaskGroupTemplate studyGroup =
                new TaskGroupTemplate(2L, "Study", studyItems);

        return List.of(
                new ScheduleRule(DayOfWeek.TUESDAY, bathroomGroup),
                new ScheduleRule(DayOfWeek.WEDNESDAY, studyGroup),
                new ScheduleRule(DayOfWeek.TUESDAY, takeOutTrash),
                new ScheduleRule(washDishes)
        );
    }
}
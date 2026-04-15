package com.francesco.choreforge.model;

import java.time.DayOfWeek;

public class ScheduleRule {

    private DayOfWeek dayOfWeek;
    private TaskGroupTemplate group;

    public ScheduleRule(DayOfWeek dayOfWeek, TaskGroupTemplate group) {
        this.dayOfWeek = dayOfWeek;
        this.group = group;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public TaskGroupTemplate getGroup() {
        return group;
    }
}

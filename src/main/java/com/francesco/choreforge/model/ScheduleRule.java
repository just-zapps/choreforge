package com.francesco.choreforge.model;

import java.time.DayOfWeek;

public class ScheduleRule {

    private DayOfWeek dayOfWeek;
    private TaskGroupTemplate group;
    private TaskTemplate taskTemplate;
    private boolean everyDay;

    public ScheduleRule(DayOfWeek dayOfWeek, TaskGroupTemplate group) {
        this.dayOfWeek = dayOfWeek;
        this.group = group;
        this.taskTemplate = null;
        this.everyDay = false;
    }

    public ScheduleRule(DayOfWeek dayOfWeek, TaskTemplate taskTemplate) {
        this.dayOfWeek = dayOfWeek;
        this.group = null;
        this.taskTemplate = taskTemplate;
        this.everyDay = false;
    }

    public ScheduleRule(TaskGroupTemplate group) {
        this.group = group;
        this.taskTemplate = null;
        this.dayOfWeek = null;
        this.everyDay = true;
    }

    public ScheduleRule(TaskTemplate taskTemplate) {
        this.group = null;
        this.taskTemplate = taskTemplate;
        this.dayOfWeek = null;
        this.everyDay = true;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public TaskGroupTemplate getGroup() {
        return group;
    }

    public TaskTemplate getTaskTemplate() {
        return taskTemplate;
    }

    public boolean isGroupRule() {
        return group != null;
    }

    public boolean isTaskRule() {
        return taskTemplate != null;
    }

    public boolean isEveryDay() {
        return everyDay;
    }
}

package com.francesco.choreforge.model;

import java.time.LocalDate;

public class TaskInstance {

    private TaskTemplate taskTemplate;
    private LocalDate date;
    private Player assignedTo;

    public TaskInstance(TaskTemplate taskTemplate, LocalDate date, Player assignedTo) {
        this.taskTemplate = taskTemplate;
        this.date = date;
        this.assignedTo = assignedTo;
    }

    public TaskTemplate getTaskTemplate() {
        return taskTemplate;
    }

    public LocalDate getDate() {
        return date;
    }

    public Player getAssignedTo() {
        return assignedTo;
    }
}

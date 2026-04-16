package com.francesco.choreforge.model;

import java.time.LocalDate;

public class TaskInstance {

    private Long id;
    private TaskTemplate taskTemplate;
    private LocalDate date;
    private Player assignedTo;
    private String groupName;
    private TaskStatus status;

    public TaskInstance(TaskTemplate taskTemplate, LocalDate date, Player assignedTo, String groupName) {
        this.id = null;
        this.taskTemplate = taskTemplate;
        this.date = date;
        this.assignedTo = assignedTo;
        this.groupName = groupName;
        this.status = status.PENDING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getGroupName() {
        return groupName;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}

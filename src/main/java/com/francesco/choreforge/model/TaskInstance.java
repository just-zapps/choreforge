package com.francesco.choreforge.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskInstance {

    private Long id;
    private TaskTemplate taskTemplate;
    private LocalDate date;
    private Player assignedTo;
    private String groupName;
    private TaskStatus status;
    private LocalDateTime completedAt;
    private LocalDateTime dueAt;

    public TaskInstance(TaskTemplate taskTemplate, LocalDate date, Player assignedTo, String groupName) {
        this.id = null;
        this.taskTemplate = taskTemplate;
        this.date = date;
        this.assignedTo = assignedTo;
        this.groupName = groupName;
        this.status = status.PENDING;
        this.completedAt = null;
        this.dueAt = date.atTime(23,59,59);
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

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getDueAt() {
        return dueAt;
    }
}

package com.francesco.choreforge.model;

import java.time.LocalDate;

public class TaskInstance {

    private String name;
    private LocalDate date;
    private String assignedTo;

    public TaskInstance(String name, LocalDate date, String assignedTo) {
        this.name = name;
        this.date = date;
        this.assignedTo = assignedTo;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getAssignedTo() {
        return assignedTo;
    }
}

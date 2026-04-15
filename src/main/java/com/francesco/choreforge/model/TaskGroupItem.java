package com.francesco.choreforge.model;

public class TaskGroupItem {

    private TaskTemplate taskTemplate;
    private boolean alwaysIncluded;

    public TaskGroupItem(TaskTemplate taskTemplate, boolean alwaysIncluded) {
        this.taskTemplate = taskTemplate;
        this.alwaysIncluded = alwaysIncluded;
    }

    public TaskTemplate getTaskTemplate() {
        return taskTemplate;
    }

    public boolean isAlwaysIncluded() {
        return alwaysIncluded;
    }
}

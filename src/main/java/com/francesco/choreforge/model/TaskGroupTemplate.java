package com.francesco.choreforge.model;

public class TaskGroupTemplate {

    private Long id;
    private String name;

    public TaskGroupTemplate(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

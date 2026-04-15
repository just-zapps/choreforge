package com.francesco.choreforge.model;

import java.util.List;

public class TaskGroupTemplate {

    private Long id;
    private String name;
    private List<TaskGroupItem> items;

    public TaskGroupTemplate(Long id, String name, List<TaskGroupItem> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TaskGroupItem> getItems() {
        return items;
    }
}

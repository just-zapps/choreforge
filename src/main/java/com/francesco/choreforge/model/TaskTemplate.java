package com.francesco.choreforge.model;

public class TaskTemplate {

    private Long id;
    private String name;
    private int points;
    private int penalty;

    public TaskTemplate(Long id, String name, int points, int penalty) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.penalty = penalty;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getPenalty() {
        return penalty;
    }
}

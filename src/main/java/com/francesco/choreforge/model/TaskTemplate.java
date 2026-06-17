package com.francesco.choreforge.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TaskTemplate {

    @Id
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

    public TaskTemplate() {

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

package com.francesco.choreforge.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Player {

    @Id
    private Long id;
    private String name;
    private int score;

    public Player(Long id, String name) {
        this.id = id;
        this.name = name;
        this.score = 0;
    }

    public Player() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void modifyScore(int points) {
        this.score += points;
    }
}

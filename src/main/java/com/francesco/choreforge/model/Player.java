package com.francesco.choreforge.model;

public class Player {

    private Long id;
    private String name;
    private int score;

    public Player(Long id, String name) {
        this.id = id;
        this.name = name;
        this.score = 0;
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

package com.francesco.choreforge.model;

public class Player {

    private Long id;
    private String name;

    public Player(Long id, String name) {
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

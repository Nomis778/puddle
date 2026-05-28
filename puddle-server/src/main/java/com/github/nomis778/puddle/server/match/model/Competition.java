package com.github.nomis778.puddle.server.match.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Competition {
    @Id
    private long id;

    private String name;
    private String emblem;

    public Competition() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmblem() {
        return emblem;
    }

    public void setEmblem(String emblem) {
        this.emblem = emblem;
    }
}

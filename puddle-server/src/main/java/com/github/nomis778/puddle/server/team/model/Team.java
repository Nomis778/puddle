package com.github.nomis778.puddle.server.team.model;

import jakarta.persistence.*;

@Entity
public class Team {
    @Id
    private long id;
    private String shortName;

    public Team() {
    }

    public Team(long id, String shortName) {
        this.id = id;
        this.shortName = shortName;
    }

    public String getShortName() { return shortName; }

    public long getId() { return id; }

    public void setShortName(String shortName) { this.shortName = shortName; }

    public void setId(long id) { this.id = id; }
}

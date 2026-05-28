package com.github.nomis778.puddle.server.match.model;

import jakarta.persistence.*;

@Entity
public class Team {
    @Id
    private long id;
    private String shortName;
    private String crest;

    public Team() {}

    public String getShortName() {
        return shortName;
    }

    public long getId() {
        return id;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCrest() {
        return crest;
    }

    public void setCrest(String crest) {
        this.crest = crest;
    }
}

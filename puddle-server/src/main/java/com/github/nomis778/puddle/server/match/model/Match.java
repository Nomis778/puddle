package com.github.nomis778.puddle.server.match.model;

import jakarta.persistence.*;
import com.github.nomis778.puddle.server.team.model.Team;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    private long id;

    @ManyToOne
    private Team homeTeam;

    @ManyToOne
    private Team awayTeam;

    public Match() {}

    public Match(long id, Team homeTeam, Team awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public String toString() {
        return "%d: %s vs %s".formatted(id, homeTeam.getShortName(), awayTeam.getShortName());
    }

    public long getId(){ return id; }

    public Team getHomeTeam() { return homeTeam; }

    public Team getAwayTeam() { return awayTeam; }

    public void setHomeTeam(Team homeTeam) { this.homeTeam = homeTeam; }

    public void setId(long id) { this.id = id; }

    public void setAwayTeam(Team awayTeam) { this.awayTeam = awayTeam; }
}

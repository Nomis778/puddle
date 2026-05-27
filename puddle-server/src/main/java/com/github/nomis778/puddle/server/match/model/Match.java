package com.github.nomis778.puddle.server.match.model;

import com.github.nomis778.puddle.server.competition.model.Competition;
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

    @ManyToOne
    private Competition competition;

    @Embedded
    private Score score;


    public Match() {}

    public String toString() {
        return "%d: %s vs %s".formatted(id, homeTeam.getShortName(), awayTeam.getShortName());
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}

package com.github.nomis778.puddle.server.match.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    private String status;

    @Column(name = "event_date", columnDefinition = "DATETIME(0)")
    private LocalDateTime utcDate;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUtcDate() {
        return utcDate;
    }

    public void setUtcDate(LocalDateTime utcDate) {
        this.utcDate = utcDate;
    }
}

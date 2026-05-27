package com.github.nomis778.puddle.server.match.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Transient;

import java.util.Map;

@Embeddable
public class Score {
    private Integer homeScore;
    private Integer awayScore;

    public Score() {}

    @JsonProperty("fullTime")
    private void unpackFullTime(Map<String, Integer> fullTime) {
        this.homeScore = fullTime.get("home");
        this.awayScore = fullTime.get("away");
    }

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }
}

package com.github.nomis778.puddle.client;

import java.time.LocalDateTime;

public record Match(long id, Competition competition, Team homeTeam, Team awayTeam, Score score, LocalDateTime utcDate) {
    public record Competition(String name) {}
    public record Team(String shortName) {}
    public record Score(int homeScore, int awayScore) {}
}

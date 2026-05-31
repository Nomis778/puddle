package com.github.nomis778.puddle.client;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public record Match(long id, Competition competition, Team homeTeam, Team awayTeam, Score score, LocalDateTime utcDate) {
    public record Competition(String name) {}
    public record Team(String shortName) {}
    public record Score(int homeScore, int awayScore) {}

    public ZonedDateTime localDate() {
        return utcDate.atZone(ZoneOffset.UTC)
                .withZoneSameInstant(ZoneId.systemDefault());
    }
}

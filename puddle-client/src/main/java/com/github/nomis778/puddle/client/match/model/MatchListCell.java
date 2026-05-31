package com.github.nomis778.puddle.client.match.model;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class MatchListCell extends ListCell<Match> {
    @Override
    public void updateItem(Match match, boolean empty) {
        super.updateItem(match, empty);
        if(empty || match == null) {
            setGraphic(null);
            return;
        }

        HBox row = new HBox(10);

        ZonedDateTime date = match.localDate();
        Label day = new Label(date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
        Label time = new Label("%02d:%02d".formatted(date.getHour(), date.getMinute()));
        Label homeTeam = new Label(match.homeTeam().shortName());

        Match.Score s = match.score();
        String scoreTxt = s == null ? (" vs ") : "%d - %d".formatted(match.score().homeScore(), match.score().awayScore());
        Label score = new Label(scoreTxt);

        Label awayTeam = new Label(match.awayTeam().shortName());
        row.getChildren().addAll(day, time, homeTeam, score, awayTeam);
        setGraphic(row);
    }
}

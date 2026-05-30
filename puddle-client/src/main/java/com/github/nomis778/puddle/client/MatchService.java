package com.github.nomis778.puddle.client;

import com.github.nomis778.puddle.client.shared.HttpSession;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MatchService {
    private final ObjectProperty<Long> selectedMatchId = new SimpleObjectProperty<>();

    public Map<String, List<Match>> fetchMatchesByCompetition() {
        RestClient client = HttpSession.getClient();
        ResponseEntity<Match[]> matches = client.get()
                .uri("/matches")
                .retrieve()
                .toEntity(Match[].class);

        return Arrays.stream(matches.getBody())
                .collect(Collectors.groupingBy((match) -> match.competition().name()));
    }

    public Match fetchSelectedMatch() {
        RestClient client = HttpSession.getClient();
        ResponseEntity<Match> matches = client.get()
                .uri("/matches/" + selectedMatchId.get())
                .retrieve()
                .toEntity(Match.class);
        return matches.getBody();
    }

    public ObjectProperty<Long> selectedMatchIdProperty() {
        return selectedMatchId;
    }
}
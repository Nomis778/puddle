package com.github.nomis778.puddle.client;

import com.github.nomis778.puddle.client.shared.HttpSession;
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
    private Map<String, List<Match>> matchesByCompetition;
    private ArrayList<ListView<Match>> allListViews = new ArrayList<>();
    private long selectedMatchId;

    public void refreshMatches(VBox box) {
        updateMatches();
        clear(box);
        populateBoxWithMatches(box);
    }

    private void updateMatches() {
        RestClient client = HttpSession.getClient();
        ResponseEntity<Match[]> matches = client.get()
                .uri("/matches")
                .retrieve()
                .toEntity(Match[].class);

        matchesByCompetition = Arrays.stream(matches.getBody())
                .collect(Collectors.groupingBy((match) -> match.competition().name()));
    }

    private void clear(VBox box) {
        allListViews.clear();
        box.getChildren().clear();
    }

    private void populateBoxWithMatches(VBox box) {
        matchesByCompetition.forEach((competition, matchList) -> {
            ListView<Match> listView = new ListView<>();
            listView.getItems().addAll(matchList);
            listView.setCellFactory(lv -> new MatchListCell());

            listView.getSelectionModel().selectedItemProperty()
                    .addListener((obs, oldVal, newVal) -> {
                        if (newVal != null) onMatchSelected(listView, newVal);
                    });
            allListViews.add(listView);

            TitledPane pane = new TitledPane(competition, listView);
            box.getChildren().add(pane);
        });
    }

    private void onMatchSelected(ListView<Match> source, Match match) {
        selectedMatchId = match.id();

        // Has to clear all other listview selections to
        // allow for new selections on these listviews
        for(var lv: allListViews) {
            if(lv != source)
                lv.getSelectionModel().clearSelection();
        }
    }
}
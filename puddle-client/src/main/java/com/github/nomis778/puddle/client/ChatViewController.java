package com.github.nomis778.puddle.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class ChatViewController implements Initializable {
    private final MatchService matchService = new MatchService();
    private ArrayList<ListView<Match>> allListViews = new ArrayList<>();

    @FXML
    VBox matchBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshMatchBox();
    }

    @FXML
    public void refreshMatchBox() {
        CompletableFuture
                .supplyAsync(matchService::fetchMatchesByCompetition)
                .thenAccept(result -> Platform.runLater(() -> populateBox(result)));
    }

    private void populateBox(Map<String, List<Match>> matchesByCompetition) {
        clearBox();

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
            matchBox.getChildren().add(pane);
        });
    }

    private void onMatchSelected(ListView<Match> source, Match match) {
        matchService.selectedMatchIdProperty().setValue(match.id());

        // Has to clear all other listview selections to
        // allow for new selections on these listviews
        for(var lv: allListViews) {
            if(lv != source)
                lv.getSelectionModel().clearSelection();
        }
    }

    private void clearBox() {
        allListViews.clear();
        matchBox.getChildren().clear();
    }
}

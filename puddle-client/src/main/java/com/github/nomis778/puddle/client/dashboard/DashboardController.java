package com.github.nomis778.puddle.client.dashboard;

import com.github.nomis778.puddle.client.chat.ChatService;
import com.github.nomis778.puddle.client.chat.model.MessageResponse;
import com.github.nomis778.puddle.client.chat.model.MessageListCell;
import com.github.nomis778.puddle.client.match.model.Match;
import com.github.nomis778.puddle.client.match.model.MatchListCell;
import com.github.nomis778.puddle.client.match.MatchService;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class DashboardController implements Initializable {
    private final MatchService matchService = new MatchService();
    private ArrayList<ListView<Match>> allListViews = new ArrayList<>();

    private ChatService chatService = new ChatService();

    @FXML
    VBox matchBox;

    @FXML
    Label homeTeamName;
    @FXML
    Label homeScore;
    @FXML
    Label awayTeamName;
    @FXML
    Label awayScore;

    @FXML
    ListView<MessageResponse> chatView;
    @FXML
    TextField chatField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        matchService.selectedMatchIdProperty().addListener((obs, old, newVal) -> {
            updateSelectedMatch();
        });
        refreshMatchBox();

        matchService.selectedMatchIdProperty().addListener((obs, old, newVal) -> {
            chatService.connectToMatch((long) newVal);
        });

        initChatView();
    }

    @FXML
    public void refresh() {
        refreshMatchBox();
        updateSelectedMatch();
    }

    @FXML
    public void sendMessage() {
        String content = chatField.getText();
        chatField.clear();
        long matchId = matchService.selectedMatchIdProperty().get();
        chatService.sendMessage(content, matchId);
    }

    private void refreshMatchBox() {
        CompletableFuture
                .supplyAsync(matchService::fetchMatchesByCompetition)
                .thenAccept(matches -> Platform.runLater(() -> {
                    clearBox();
                    populateBox(matches);
                }));
    }

    private void populateBox(Map<String, List<Match>> matchesByCompetition) {
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

    private void updateSelectedMatch() {
        CompletableFuture
                .supplyAsync(matchService::fetchSelectedMatch)
                .thenAccept(match -> Platform.runLater(() -> {
                    homeTeamName.setText(match.homeTeam().shortName());
                    awayTeamName.setText(match.awayTeam().shortName());

                    Match.Score score = match.score();
                    if(score != null) {
                        homeScore.setText(score.homeScore() + "");
                        awayScore.setText(score.awayScore() + "");
                    } else {
                        homeScore.setText("");
                        awayScore.setText("");
                    }
                }));
    }

    private void initChatView() {
        var messages = chatService.getMessages();
        chatView.setItems(messages);
        chatView.setCellFactory(lv -> new MessageListCell());
        messages.addListener((ListChangeListener<MessageResponse>) change -> {
            Platform.runLater(() -> chatView.scrollTo(messages.size() - 1));
        });
    }
}

package com.github.nomis778.puddle.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatViewController implements Initializable {
    private final MatchService matchService = new MatchService();

    @FXML
    VBox matchBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        matchService.refreshMatches(matchBox);
    }

    @FXML
    public void refreshMatchBox() {
        matchService.refreshMatches(matchBox);
    }
}

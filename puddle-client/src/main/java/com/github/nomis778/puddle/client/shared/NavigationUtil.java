package com.github.nomis778.puddle.client.shared;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationUtil {
    public static void navigateTo(Event event, String FXMLPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(NavigationUtil.class.getResource("../" + FXMLPath));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}

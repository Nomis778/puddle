package com.github.nomis778.puddle.client.auth;

import com.github.nomis778.puddle.client.shared.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void register(ActionEvent e) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        AuthService.register(username, password);
        NavigationUtil.navigateTo(e, "chat_view.fxml");
    }

    @FXML
    public void navigateToLoginPage(MouseEvent e) throws IOException {
        NavigationUtil.navigateTo(e, "auth/login.fxml");
    }
}

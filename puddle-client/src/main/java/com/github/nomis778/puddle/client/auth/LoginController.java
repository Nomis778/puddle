package com.github.nomis778.puddle.client.auth;

import com.github.nomis778.puddle.client.shared.NavigationUtil;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void logIn() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        AuthService.logIn(username, password);
    }

    @FXML
    public void navigateToRegisterPage(MouseEvent e) throws IOException {
        NavigationUtil.navigateTo(e, "auth/register.fxml");
    }
}

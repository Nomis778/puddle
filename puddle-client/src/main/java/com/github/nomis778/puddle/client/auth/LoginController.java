package com.github.nomis778.puddle.client.auth;

import com.github.nomis778.puddle.client.shared.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    public void logIn(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        passwordField.clear();

        if(username.isBlank() || password.isBlank())
            return;

        try {
            AuthService.logIn(username, password);
            NavigationUtil.navigateTo(event, "dashboard/dashboard.fxml");
        } catch (HttpClientErrorException e) {
            errorLabel.setText(e.getResponseBodyAsString());
        }
    }

    @FXML
    public void navigateToRegisterPage(MouseEvent e) throws IOException {
        NavigationUtil.navigateTo(e, "auth/register.fxml");
    }
}

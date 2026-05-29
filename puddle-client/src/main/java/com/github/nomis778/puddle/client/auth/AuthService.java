package com.github.nomis778.puddle.client.auth;

import com.github.nomis778.puddle.client.shared.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

public class AuthService {
    private static final RestClient client = HttpSession.getClient();

    public static void logIn(String username, String password) {
        RestClient client = HttpSession.getClient();
        ResponseEntity<String> response = client.post()
                .uri("/auth/login")
                .body(new LoginDetails(username, password))
                .retrieve()
                .toEntity(String.class);

        HttpSession.setJwt(response.getBody());
    }

    public static void register(String username, String password) {
        RestClient client = HttpSession.getClient();
        ResponseEntity<String> response = client.post()
                .uri("/auth/register")
                .body(new LoginDetails(username, password))
                .retrieve()
                .toEntity(String.class);
    }
}

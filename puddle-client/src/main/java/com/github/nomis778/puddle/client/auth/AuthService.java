package com.github.nomis778.puddle.client.auth;

import com.github.nomis778.puddle.client.shared.HttpSession;
import org.springframework.web.client.RestClient;

public class AuthService {
    private static final RestClient client = HttpSession.getClient();

    public static void logIn(String username, String password) {
        System.out.println("LOGIN: username: %s, password: %s".formatted(username, password));
    }

    public static void register(String username, String password) {
        System.out.println("REGISTER:  username: %s, password: %s".formatted(username, password));
    }
}

package com.github.nomis778.puddle.client;

public class ClientService {

    String DB_URL;
    String DB_USER;
    String DB_PASSWORD;

    public boolean isCorrectPassword(String email, String password) {
        System.out.println();
        return password.equals("correct!");
    }
}

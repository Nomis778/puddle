package com.github.nomis778.puddle.client.shared;

import org.springframework.web.client.RestClient;

public class HttpSession {
    private static final RestClient client = RestClient.builder()
            .baseUrl("http://localhost:8080")
            .requestInterceptor((request, body, execution) -> {
                if (HttpSession.jwt != null) {
                    request.getHeaders().add("Authorization", "Bearer " + HttpSession.jwt);
                }
                return execution.execute(request, body);
            })
            .build();

    private static String jwt;

    private HttpSession() {}

    public static RestClient getClient() {
        return client;
    }

    public static void setJwt(String jwt) {
        HttpSession.jwt = jwt;
    }

    public static void clearJwt() {
        jwt = null;
    }
}

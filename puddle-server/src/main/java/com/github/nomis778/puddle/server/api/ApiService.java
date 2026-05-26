package com.github.nomis778.puddle.server.api;

import com.github.nomis778.puddle.server.api.model.Response;
import com.github.nomis778.puddle.server.match.model.Match;
import com.github.nomis778.puddle.server.team.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

public class ApiService {

    @Value("${api.key}")
    private String apiKey;

    private final RestClient footballAPI;

    public ApiService(RestClient.Builder restClientBuilder) {
        this.footballAPI = restClientBuilder
                .baseUrl("http://api.football-data.org/v4")
                .build();
    }

    public Team[] getAllTeams() {
        return footballAPI.get()
                .uri("/teams/")
                .header("X-Auth-Token", apiKey)
                .retrieve().body(Team[].class);
    }

    public Match[] getCurrentMatches() {
        LocalDate today = LocalDate.now();
        String dateFrom = today.minusDays(2).toString();
        String dateTo = today.plusDays(2).toString();

        Response r = footballAPI.get()
                .uri(uri -> uri
                        .path("/matches")
                        .queryParam("dateFrom", dateFrom)
                        .queryParam("dateTo", dateTo)
                        .build())
                .header("X-Auth-Token", apiKey)
                .retrieve().body(Response.class);

        return r.matches();
    }
}

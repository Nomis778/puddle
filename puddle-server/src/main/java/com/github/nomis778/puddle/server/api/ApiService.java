package com.github.nomis778.puddle.server.api;

import com.github.nomis778.puddle.server.api.model.MatchResponse;
import com.github.nomis778.puddle.server.match.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

@Service
public class ApiService {
    private static Logger log = LoggerFactory.getLogger(ApiService.class);

    @Value("${api.key}")
    private String apiKey;

    private final RestClient footballAPI;

    public ApiService(RestClient.Builder restClientBuilder) {
        this.footballAPI = restClientBuilder
                .baseUrl("http://api.football-data.org/v4")
                .build();
    }

    public Match[] getOldMatches() {
        LocalDate today = LocalDate.now();
        String dateFrom = today.minusDays(4).toString();
        String dateTo = today.minusDays(2).toString();

        MatchResponse mr = footballAPI.get()
                .uri(uri -> uri
                        .path("/matches")
                        .queryParam("dateFrom", dateFrom)
                        .queryParam("dateTo", dateTo)
                        .build())
                .header("X-Auth-Token", apiKey)
                .retrieve().body(MatchResponse.class);

        return mr.matches();
    }

    public Match[] getCurrentMatches() {
        LocalDate today = LocalDate.now();
        String dateFrom = today.minusDays(2).toString();
        String dateTo = today.plusDays(2).toString();

        MatchResponse mr = footballAPI.get()
                .uri(uri -> uri
                        .path("/matches")
                        .queryParam("dateFrom", dateFrom)
                        .queryParam("dateTo", dateTo)
                        .build())
                .header("X-Auth-Token", apiKey)
                .retrieve().body(MatchResponse.class);

        return mr.matches();
    }
}

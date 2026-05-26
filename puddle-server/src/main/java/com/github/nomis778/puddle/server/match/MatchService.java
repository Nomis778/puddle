package com.github.nomis778.puddle.server.match;

import com.github.nomis778.puddle.server.match.model.Match;
import com.github.nomis778.puddle.server.match.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

public class MatchService {
    private static final Logger log = LoggerFactory.getLogger(MatchScheduler.class);

    @Value("${api.key}")
    private String apiKey;

    private final RestClient footballAPI;

    public MatchService(RestClient.Builder restClientBuilder) {
        this.footballAPI = restClientBuilder
                .baseUrl("http://api.football-data.org/v4")
                .build();
    }

    public void updateMatches() {
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

        for(Match m: r.matches())
            log.info(m.toString());
    }
}

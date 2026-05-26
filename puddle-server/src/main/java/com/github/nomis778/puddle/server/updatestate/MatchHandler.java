package com.github.nomis778.puddle.server.updatestate;

import com.github.nomis778.puddle.server.updatestate.model.Match;
import com.github.nomis778.puddle.server.updatestate.model.Response;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

@Component
public class MatchHandler {

    private static final Logger log = LoggerFactory.getLogger(MatchHandler.class);

    @Value("${api.key}")
    private String apiKey;

    private final RestClient footballAPI;

    public MatchHandler(RestClient.Builder restClientBuilder) {
        this.footballAPI = restClientBuilder
                .baseUrl("http://api.football-data.org/v4")
                .build();
    }

    @PostConstruct
    public void init() {
        updateMatches();
    }

    // Scheduled at the start of every day
    @Scheduled(cron = "0 0 0 * * *")
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
            log.info("%d: %s vs %s".formatted(m.id(), m.homeTeam().shortName(), m.awayTeam().shortName()));
    }
}

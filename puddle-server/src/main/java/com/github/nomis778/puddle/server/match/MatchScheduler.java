package com.github.nomis778.puddle.server.match;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MatchScheduler {
    private final MatchService matchService;

    @Autowired
    public MatchScheduler(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostConstruct
    public void init() {
        matchService.dropAllMatches();
        matchService.addNewMatches();
    }

    // Scheduled at the start of every day
    @Scheduled(cron = "0 0 0 * * *")
    public void scheduledUpdate() {
        matchService.dropOldMatches();
        matchService.addNewMatches();
    }
}

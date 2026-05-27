package com.github.nomis778.puddle.server.match;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MatchScheduler {
    private static final int TWICE_PER_MINUTE = 30000;

    private final MatchService matchService;

    @Autowired
    public MatchScheduler(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostConstruct
    public void init() {
        matchService.dropAllMatches();
        matchService.updateMatches();
    }

    // Scheduled at the start of every day
    @Scheduled(cron = "0 0 0 * * *")
    public void scheduledMatchUpdate() {
        matchService.dropOldMatches();
        matchService.updateMatches();
    }

    @Scheduled(fixedRate = TWICE_PER_MINUTE)
    public void scheduledMatchInfoUpdate() {
        matchService.updateMatches();
    }
}

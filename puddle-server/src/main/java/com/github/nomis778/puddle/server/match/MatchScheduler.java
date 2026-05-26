package com.github.nomis778.puddle.server.match;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MatchScheduler {
    private final MatchService service;

    @Autowired
    public MatchScheduler(MatchService service) {
        this.service = service;
    }

    @PostConstruct
    public void init() {
        service.updateMatches();
    }

    // Scheduled at the start of every day
    @Scheduled(cron = "0 0 0 * * *")
    public void scheduledUpdate() {
        service.updateMatches();
    }
}

package com.github.nomis778.puddle.server.match;

import com.github.nomis778.puddle.server.match.model.Match;
import com.github.nomis778.puddle.server.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClient;

public class MatchService {
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }

    public void updateMatches() {
        for(Match m: r.matches())
            matchRepository.save(m);
    }
}

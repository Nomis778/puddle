package com.github.nomis778.puddle.server.match;

import com.github.nomis778.puddle.server.api.ApiService;
import com.github.nomis778.puddle.server.match.model.Match;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
    private final ApiService apiService;

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final CompetitionRepository competitionRepository;

    @Autowired
    public MatchService(ApiService apiService,
                        MatchRepository matchRepository,
                        TeamRepository teamRepository,
                        CompetitionRepository competitionRepository) {

        this.apiService = apiService;
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.competitionRepository = competitionRepository;
    }

    public void updateMatches() {
        Match[] matches = apiService.getCurrentMatches();
        for(Match m: matches) {
            teamRepository.save(m.getHomeTeam());
            teamRepository.save(m.getAwayTeam());
            competitionRepository.save(m.getCompetition());
            matchRepository.save(m);
        }
    }

    public void dropOldMatches() {
        Match[] matches = apiService.getOldMatches();
        for(Match m: matches) {
            matchRepository.delete(m);
        }
    }

    public void dropAllMatches() {
        matchRepository.deleteAll();
    }

    public List<Match> getCurrentMatches() {
        return matchRepository.findAll();
    }

    public Match getMatch(long id) {
        Optional<Match> m = matchRepository.findById(id);
        if(m.isEmpty())
            throw new EntityNotFoundException("Error: No such match exists!");
        return m.get();
    }
}

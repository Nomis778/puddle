package com.github.nomis778.puddle.server.match;

import com.github.nomis778.puddle.server.match.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {}

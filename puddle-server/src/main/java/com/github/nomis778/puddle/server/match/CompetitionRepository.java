package com.github.nomis778.puddle.server.match;

import com.github.nomis778.puddle.server.match.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {}

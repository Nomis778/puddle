package com.github.nomis778.puddle.server.competition;

import com.github.nomis778.puddle.server.competition.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {}

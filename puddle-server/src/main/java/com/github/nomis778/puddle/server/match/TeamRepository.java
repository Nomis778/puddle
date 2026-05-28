package com.github.nomis778.puddle.server.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.nomis778.puddle.server.match.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {}
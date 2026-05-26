package com.github.nomis778.puddle.server.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.nomis778.puddle.server.team.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {}
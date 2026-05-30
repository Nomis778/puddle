package com.github.nomis778.puddle.server.ws;

import com.github.nomis778.puddle.server.ws.model.Message;
import com.github.nomis778.puddle.server.ws.model.MessageResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    public List<Message> findAllByMatchId(long matchId);
}

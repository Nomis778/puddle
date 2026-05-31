package com.github.nomis778.puddle.server.chat;

import com.github.nomis778.puddle.server.chat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    public List<Message> findAllByMatchId(long matchId);
}

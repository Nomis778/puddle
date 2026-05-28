package com.github.nomis778.puddle.server.ws;

import com.github.nomis778.puddle.server.ws.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {}

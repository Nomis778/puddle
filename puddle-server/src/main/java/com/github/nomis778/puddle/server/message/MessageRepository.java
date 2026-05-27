package com.github.nomis778.puddle.server.message;

import com.github.nomis778.puddle.server.message.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {}

package com.github.nomis778.puddle.server.ws;

import com.github.nomis778.puddle.server.ws.model.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {
    SimpMessagingTemplate messagingTemplate;

    MessageRepository messageRepository;

    public MessageService(SimpMessagingTemplate messagingTemplate, MessageRepository messageRepository) {
        this.messagingTemplate = messagingTemplate;
        this.messageRepository = messageRepository;
    }

    public void send(Message msg) {
        long matchId = msg.getMatchId();
        msg.setTimeStamp(LocalDateTime.now());

        messageRepository.save(msg);
        messagingTemplate.convertAndSend("/topic/" + matchId, msg);
    }
}

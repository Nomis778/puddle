package com.github.nomis778.puddle.server.ws;

import com.github.nomis778.puddle.server.user.UserService;
import com.github.nomis778.puddle.server.user.model.User;
import com.github.nomis778.puddle.server.ws.model.Message;
import com.github.nomis778.puddle.server.ws.model.MessageRequest;
import com.github.nomis778.puddle.server.ws.model.MessageResponse;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    SimpMessagingTemplate messagingTemplate;

    UserService userService;
    MessageRepository messageRepository;

    public MessageService(SimpMessagingTemplate messagingTemplate,
                          UserService userService,
                          MessageRepository messageRepository) {

        this.messagingTemplate = messagingTemplate;
        this.userService = userService;
        this.messageRepository = messageRepository;
    }

    public void send(long userID, MessageRequest msgRequest) {
        User sender = userService.getUserById(userID);
        Message msg = msgRequest.toMessage(sender);
        messageRepository.save(msg);

        MessageResponse msgResponse = new MessageResponse(msg);
        long matchId = msgRequest.getMatchId();
        messagingTemplate.convertAndSend("/topic/" + matchId, msgResponse);
    }
}

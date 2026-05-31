package com.github.nomis778.puddle.server.chat;

import com.github.nomis778.puddle.server.user.UserService;
import com.github.nomis778.puddle.server.user.model.User;
import com.github.nomis778.puddle.server.chat.model.Message;
import com.github.nomis778.puddle.server.chat.model.MessageRequest;
import com.github.nomis778.puddle.server.chat.model.MessageResponse;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<MessageResponse> getMessages(long matchId) {
        List<Message> messages = messageRepository.findAllByMatchId(matchId);
        List<MessageResponse> msgResponses = messages.stream()
                .map(MessageResponse::new)
                .toList();
        return msgResponses;
    }
}

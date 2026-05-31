package com.github.nomis778.puddle.server.chat;

import com.github.nomis778.puddle.server.chat.model.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MessageWsController {
    MessageService messageService;

    @Autowired
    public MessageWsController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/send")
    public void send(Principal principal, @Payload MessageRequest mr) {
        String strId = principal.getName();
        messageService.send(Long.parseLong(strId), mr);
    }
}

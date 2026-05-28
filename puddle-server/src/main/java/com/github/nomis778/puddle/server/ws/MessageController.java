package com.github.nomis778.puddle.server.ws;

import com.github.nomis778.puddle.server.ws.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/send")
    public void send(@Payload Message msg) {
        messageService.send(msg);
    }
}

package com.github.nomis778.puddle.server.ws;

import com.github.nomis778.puddle.server.ws.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageRestController {
    private final MessageService messageService;

    @Autowired
    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<MessageResponse>> getSentMessagesByMatchId(@PathVariable("id") long matchId) {
        List<MessageResponse> mrs = messageService.getMessages(matchId);
        return new ResponseEntity<>(mrs, HttpStatus.OK);
    }
}

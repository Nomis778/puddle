package com.github.nomis778.puddle.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.nomis778.puddle.client.chat.MessageRequest;
import com.github.nomis778.puddle.client.chat.MessageResponse;
import com.github.nomis778.puddle.client.chat.PuddleStompSessionHandler;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.Scanner;

public class CommandLine {
    public static void main(String[] args) throws Exception {
        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);

        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        converter.setObjectMapper(mapper);
        stompClient.setMessageConverter(converter);

        String url = "ws://localhost:8080/ws";

        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzc5OTk5MDAwLCJleHAiOjE3ODAwMDI2MDB9.2xxTo4PuannAflSOt-8P4NFJGFlxFqFfcECVSJWDv5I";
        WebSocketHttpHeaders handshakeHeaders = new WebSocketHttpHeaders();
        handshakeHeaders.add("Authorization", "Bearer " + jwtToken);

        StompSessionHandler sessionHandler = new PuddleStompSessionHandler();

        StompSession session = stompClient.connectAsync(url,
                handshakeHeaders,
                sessionHandler).get();

        session.subscribe("/topic/" + 557166, new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders headers) {
                return MessageResponse.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                MessageResponse msg = (MessageResponse) payload;
                System.out.println("(%s) %s: %s".formatted(msg.getTimeStamp(), msg.getSender().username(), msg.getContent()));
            }

        });

        Scanner scn = new Scanner(System.in);
        while(true) {
            MessageRequest msg = new MessageRequest(scn.nextLine(), 557166);
            session.send("/chat/send", msg);
        }
    }
}

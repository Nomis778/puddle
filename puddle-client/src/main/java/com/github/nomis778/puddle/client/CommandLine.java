package com.github.nomis778.puddle.client;

import jakarta.websocket.ContainerProvider;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Scanner;

public class CommandLine {
    public static void main(String[] args) throws Exception {
        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        String url = "ws://localhost:8080/ws";
        StompSessionHandler sessionHandler = new PuddleStompSessionHandler();
        StompSession session = stompClient.connectAsync(url, sessionHandler).get();

        session.subscribe("/topic/" + 557166, new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Message.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                Message msg = (Message) payload;
                System.out.println(msg.getContent());
            }

        });

        Scanner scn = new Scanner(System.in);
        while(true) {
            Message msg = new Message("Peter", scn.nextLine(), 557166);
            session.send("/chat/send", msg);
        }
    }
}

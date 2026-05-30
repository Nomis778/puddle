package com.github.nomis778.puddle.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.nomis778.puddle.client.chat.MessageResponse;
import com.github.nomis778.puddle.client.shared.HttpSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class ChatService {
    private static final String WS_URL = "ws://localhost:8080/ws";
    private WebSocketStompClient stompClient;
    private StompSession session;
    private StompSession.Subscription activeSub;

    private final ObservableList<MessageResponse> messages = FXCollections.observableArrayList();

    public ChatService() {
        stompClient = new WebSocketStompClient(new StandardWebSocketClient());

        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        converter.setObjectMapper(mapper);
        stompClient.setMessageConverter(converter);

        WebSocketHttpHeaders handshakeHeaders = new WebSocketHttpHeaders();
        handshakeHeaders.add("Authorization", "Bearer " + HttpSession.getJwt());

        stompClient.connectAsync(WS_URL, handshakeHeaders, new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession s, StompHeaders connectedHeaders) {
                session = s;
            }

            @Override
            public void handleException(StompSession s, StompCommand cmd, StompHeaders h, byte[] payload, Throwable ex) {
                System.out.println("STOMP error: " + ex.getMessage());
            }

            @Override
            public void handleTransportError(StompSession s, Throwable ex) {
                System.out.println("Transport error: " + ex.getMessage());
            }
        });
    }

    public void connectToMatch(long matchId) {
        if (activeSub != null) {
            activeSub.unsubscribe();
        }
        messages.clear();
        messages.addAll(fetchOldMessages(matchId));
        messages.add(new MessageResponse("Welcome to the chat!"));
        subscribe(matchId);
    }

    public ObservableList<MessageResponse> getMessages() {
        return messages;
    }

    private void subscribe(long matchId) {
        activeSub = session.subscribe("/topic/" + matchId, new StompFrameHandler() {

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
    }

    private MessageResponse[] fetchOldMessages(long matchId) {
        MessageResponse mr = new MessageResponse();
        mr.setSender(new PublicUser(1, "SVERKER"));
        mr.setContent("OLD MESSAGEadwa");
        mr.setTimeStamp(LocalDateTime.now());
        return new MessageResponse[] {mr};
    }
}

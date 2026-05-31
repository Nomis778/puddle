package com.github.nomis778.puddle.client.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.nomis778.puddle.client.chat.model.MessageRequest;
import com.github.nomis778.puddle.client.chat.model.MessageResponse;
import com.github.nomis778.puddle.client.shared.HttpSession;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;

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
                System.err.println("STOMP error: " + ex.getMessage());
            }

            @Override
            public void handleTransportError(StompSession s, Throwable ex) {
                System.err.println("Transport error: " + ex.getMessage());
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

    public void sendMessage(String content, long matchId) {
        if(!content.isBlank() && matchId != 0) {
            MessageRequest msg = new MessageRequest(content, matchId);
            session.send("/chat/send", msg);
        }
    }

    public void disconnect() {
        if(activeSub != null){
            activeSub.unsubscribe();
            activeSub = null;
        }
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
        stompClient.stop();
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
                Platform.runLater(() -> messages.add(msg));
            }
        });
    }

    private MessageResponse[] fetchOldMessages(long matchId) {
        RestClient client = HttpSession.getClient();
        ResponseEntity<MessageResponse[]> messages = client.get()
                .uri("/messages/" + matchId)
                .retrieve()
                .toEntity(MessageResponse[].class);
        return messages.getBody();
    }
}

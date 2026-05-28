package com.github.nomis778.puddle.client;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

public class PuddleStompSessionHandler extends StompSessionHandlerAdapter {
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("CONNECTED");
    }

    @Override
    public void handleException(StompSession s, StompCommand cmd, StompHeaders h, byte[] payload, Throwable ex) {
        System.out.println("STOMP error: " + ex.getMessage());
    }

    @Override
    public void handleTransportError(StompSession s, Throwable ex) {
        System.out.println("Transport error: " + ex.getMessage());
    }
}

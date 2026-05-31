package com.github.nomis778.puddle.client.chat.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class MessageResponse {
    private PublicUser sender;
    private long matchId;
    private String content;
    LocalDateTime timeStamp;

    public MessageResponse() {}

    public MessageResponse(String content) {
        this.content = content;
    }

    public PublicUser getSender() {
        return sender;
    }

    public void setSender(PublicUser sender) {
        this.sender = sender;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ZonedDateTime getLocalTimeStamp() {
        if(timeStamp == null)
            return null;
        return timeStamp.atZone(ZoneOffset.UTC)
                .withZoneSameInstant(ZoneId.systemDefault());
    }
}
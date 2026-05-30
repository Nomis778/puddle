package com.github.nomis778.puddle.client.chat;

import com.github.nomis778.puddle.client.PublicUser;

import java.time.LocalDateTime;

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
}
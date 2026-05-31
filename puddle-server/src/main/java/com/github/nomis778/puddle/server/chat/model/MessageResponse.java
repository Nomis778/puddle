package com.github.nomis778.puddle.server.chat.model;

import com.github.nomis778.puddle.server.user.model.PublicUser;

import java.time.Instant;

public class MessageResponse {
    private PublicUser sender;
    private long matchId;
    private String content;
    Instant timeStamp;

    public MessageResponse() {}

    public MessageResponse(Message msg) {
        this.sender = new PublicUser(msg.getSender());
        this.matchId = msg.getMatchId();
        this.content = msg.getContent();
        this.timeStamp = msg.getTimeStamp();
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

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }
}

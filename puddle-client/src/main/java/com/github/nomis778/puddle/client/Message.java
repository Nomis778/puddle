package com.github.nomis778.puddle.client;

public class Message {
    private String sender;
    private String content;
    private long matchId;

    public Message() {} // required by Jackson

    public Message(String sender, String content, long matchId) {
        this.sender = sender;
        this.content = content;
        this.matchId = matchId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }
}
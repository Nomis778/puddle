package com.github.nomis778.puddle.client;

public class MessageRequest {
    private String content;
    private long matchId;

    public MessageRequest() {}

    public MessageRequest(String content, long matchId) {
        this.content = content;
        this.matchId = matchId;
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
package com.github.nomis778.puddle.server.chat.model;

import com.github.nomis778.puddle.server.user.model.User;

public class MessageRequest {
    private String content;
    private long matchId;

    public MessageRequest() {}

    public Message toMessage(User sender) {
        return new Message(sender, matchId, content);
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

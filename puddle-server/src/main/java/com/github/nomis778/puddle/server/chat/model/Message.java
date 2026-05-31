package com.github.nomis778.puddle.server.chat.model;

import com.github.nomis778.puddle.server.user.model.User;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    private User sender;

    private long matchId;
    private String content;

    Instant timeStamp;

    public Message() {}

    public Message(User sender, long matchId, String content) {
        this.sender = sender;
        this.matchId = matchId;
        this.content = content;
        this.timeStamp = Instant.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
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

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }
}

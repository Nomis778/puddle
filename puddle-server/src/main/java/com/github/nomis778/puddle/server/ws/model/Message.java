package com.github.nomis778.puddle.server.ws.model;

import com.github.nomis778.puddle.server.user.model.PublicUser;
import com.github.nomis778.puddle.server.user.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    private User sender;

    private long matchId;
    private String content;

    LocalDateTime timeStamp;

    public Message() {}

    public Message(User sender, long matchId, String content) {
        this.sender = sender;
        this.matchId = matchId;
        this.content = content;
        this.timeStamp = LocalDateTime.now();
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

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}

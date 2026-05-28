package com.github.nomis778.puddle.server.user.model;

public class PublicUser {
    private long id;
    private String username;

    public PublicUser() {}

    public PublicUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package com.app.model;

import java.util.UUID;

public class Administrator {
    private final UUID id;
    private String login;
    private String password;


    public Administrator(UUID id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

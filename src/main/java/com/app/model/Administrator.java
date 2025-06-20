package com.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Administrator {
    private final UUID id;
    private String login;
    private String password;


    public Administrator(UUID id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

}

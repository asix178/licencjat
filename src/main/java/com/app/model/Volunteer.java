package com.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Volunteer {
    private final UUID id;
    private String name;
    private String password;

    public Volunteer(UUID id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

}

package com.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Prize {
    private final UUID id;
    private String name;
    private Category category;

    public Prize(UUID id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Prize(String name, Category category) {
         id = UUID.randomUUID();
        this.name = name;
        this.category = category;
    }

}

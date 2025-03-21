package com.app.model;

public class Accolade {
    private Long id;
    private String name;
    private Cathegory cathegory;

    public Accolade(Long id, String name, Cathegory cathegory) {
        this.id = id;
        this.name = name;
        this.cathegory = cathegory;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cathegory getCathegory() {
        return cathegory;
    }

    public void setCathegory(Cathegory cathegory) {
        this.cathegory = cathegory;
    }
}

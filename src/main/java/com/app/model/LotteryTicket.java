package com.app.model;

public class LotteryTicket {
    private Long id;
    private String number;
    private Cathegory cathegory;
    private Accolade accolade;
    private Boolean isUsed = false;

    public LotteryTicket(Long id, String number, Cathegory cathegory, Accolade accolade, Boolean isUsed) {
        this.id = id;
        this.number = number;
        this.cathegory = cathegory;
        this.accolade = accolade;
        this.isUsed = isUsed;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Cathegory getCathegory() {
        return cathegory;
    }

    public void setCathegory(Cathegory cathegory) {
        this.cathegory = cathegory;
    }

    public Accolade getAccolade() {
        return accolade;
    }

    public void setAccolade(Accolade accolade) {
        this.accolade = accolade;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }
}

package com.app.model;

public class LotteryTicket {
    private final Long id;
    private String number;
    private Category category;
    private Prize prize;
    private Boolean isUsed = false;

    public LotteryTicket(Long id, String number, Category category, Prize prize, Boolean isUsed) {
        this.id = id;
        this.number = number;
        this.category = category;
        this.prize = prize;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Prize getAccolade() {
        return prize;
    }

    public void setAccolade(Prize accolade) {
        this.prize = accolade;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }
}

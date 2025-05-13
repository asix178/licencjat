package com.app.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final Long id;
    private String login;
    private List<LotteryTicket> lotteryTickets;

    public User(Long id, String login) {
        this.id = id;
        this.login = login;
        lotteryTickets = new ArrayList<LotteryTicket>();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<LotteryTicket> getLotteryTickets() {
        return lotteryTickets;
    }

    public void setLotteryTickets(List<LotteryTicket> lotteryTickets) {
        this.lotteryTickets = lotteryTickets;
    }
}

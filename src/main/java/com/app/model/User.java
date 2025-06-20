package com.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class User {

    private final UUID id;
    private String login;
    private String password;
    private List<LotteryTicket> lotteryTickets;
    private Boolean isWinner;

    public User(UUID id, String login, String password, List<LotteryTicket> lotteryTickets) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.lotteryTickets = lotteryTickets;
        this.isWinner = false;
    }

    public User(UUID id, String login, String password,  Boolean isWinner, List<LotteryTicket> lotteryTickets) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.lotteryTickets = lotteryTickets;
        this.isWinner = isWinner;
    }
}

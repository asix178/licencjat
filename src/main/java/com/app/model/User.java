package com.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class User {

    private final UUID id;
    private String login;
    private String password;
    private List<LotteryTicket> lotteryTickets;

    public User(UUID id, String login, String password, List<LotteryTicket> lotteryTickets) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.lotteryTickets = lotteryTickets;
    }

}

package com.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LotteryTicket {
    private final UUID id;
    private Long number;
    private Category category;
    private Prize prize;
    private Boolean isUsed;

    public LotteryTicket(UUID id, Long number, Category category, Prize prize, Boolean isUsed) {
        this.id = id;
        this.number = number;
        this.category = category;
        this.prize = prize;
        this.isUsed = isUsed;
    }

}

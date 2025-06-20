package com.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LotteryTicket {
    private final UUID id;
    private String number;
    private Category category;
    private Prize prize;
    private Boolean isUsed;
    private UUID volunteerUuid;

    public LotteryTicket(UUID id, String number, Category category, Prize prize, Boolean isUsed, UUID volunteerUuid) {
        this.id = id;
        this.number = number;
        this.category = category;
        this.prize = prize;
        this.isUsed = isUsed;
        this.volunteerUuid = volunteerUuid;
    }

}

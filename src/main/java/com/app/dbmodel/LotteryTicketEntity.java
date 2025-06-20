package com.app.dbmodel;


import com.app.model.LotteryTicket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lottery_ticket")
public class LotteryTicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private UUID domainId;
    private String number;
    @ManyToOne
    private CategoryEntity category;
    @OneToOne
    private PrizeEntity prize;
    private Boolean isUsed = false;
    private UUID volunteerUuid;

    public static LotteryTicketEntity fromDomain(LotteryTicket lotteryTicket) {
        return new LotteryTicketEntity(null, lotteryTicket.getId(), lotteryTicket.getNumber(), null
                , null, lotteryTicket.getIsUsed(), lotteryTicket.getVolunteerUuid());
    }

    public LotteryTicket toDomain() {
        return new LotteryTicket(domainId, number, category.toDomain(),prize.toDomain(), isUsed, null);
    }
}

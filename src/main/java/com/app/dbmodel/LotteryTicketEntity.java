package com.app.dbmodel;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String number;
    @ManyToOne
    private CategoryEntity category;
    @OneToOne
    private PrizeEntity prize;
    private Boolean isUsed = false;
}

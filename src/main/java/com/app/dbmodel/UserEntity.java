package com.app.dbmodel;


import com.app.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID domainId;
    private String login;
    private String password;
    private Boolean isWinner;
    @OneToMany
    private List<LotteryTicketEntity> lotteryTickets;

    //change from domain class
    public static UserEntity fromDomain(User user) {
        return new UserEntity(null,user.getId(), user.getLogin(), user.getPassword(),false,null);
    }


    //change to domain class
    public User toDomain(){
        return new User(domainId, login, password, isWinner, lotteryTickets.stream().map(LotteryTicketEntity::toDomain).toList());
    }
}

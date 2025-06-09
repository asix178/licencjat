package com.app.dbmodel;

import com.app.dbmodel.id.UserTicketId;
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
@Table(name = "user_ticket")
@IdClass(UserTicketId.class)
public class UserTicketEntity {
    @Id
    @Column(name = "user_id")
    private UUID userId;
    @Id
    @Column(name = "ticket_id")
    private UUID ticketId;
}

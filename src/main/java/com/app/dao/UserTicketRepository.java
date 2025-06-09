package com.app.dao;

import com.app.dbmodel.UserTicketEntity;
import com.app.dbmodel.id.UserTicketId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserTicketRepository extends JpaRepository<UserTicketEntity, UserTicketId> {
    Optional<UserTicketEntity> findByTicketId(UUID ticketId);
}

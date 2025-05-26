package com.app.dao;


import com.app.dbmodel.LotteryTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LotteryTicketRepository extends JpaRepository<LotteryTicketEntity, Long> {
    Optional<LotteryTicketEntity> findByDomainId(UUID id);

}

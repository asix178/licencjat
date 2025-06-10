package com.app.dao;


import com.app.dbmodel.LotteryTicketEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface LotteryTicketRepository extends JpaRepository<LotteryTicketEntity, Long> {
    Optional<LotteryTicketEntity> findByDomainId(UUID id);

    Optional<LotteryTicketEntity> findByNumber(String number);

    @Query("UPDATE LotteryTicketEntity lt SET lt.isUsed = true, lt.volunteerUuid = :volunteerUuid WHERE lt.domainId = :id")
    @Modifying
    @Transactional
    void setIsUsed(UUID id, UUID volunteerUuid);

}

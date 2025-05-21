package com.app.dao;

import com.app.dbmodel.LotteryTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryTicketRepository extends JpaRepository<LotteryTicketEntity,Long> {

}

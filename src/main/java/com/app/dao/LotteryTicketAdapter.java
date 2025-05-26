package com.app.dao;

import com.app.dbmodel.LotteryTicketEntity;
import com.app.dbmodel.PrizeEntity;
import com.app.model.LotteryTicket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LotteryTicketAdapter {
    private final LotteryTicketRepository lotteryTicketRepository;
    private final PrizeRepository prizeRepository;

    public List<LotteryTicket> getAll() {
        List<LotteryTicketEntity> lotteryTicketEntityList = lotteryTicketRepository.findAll();
        return lotteryTicketEntityList.stream().map(LotteryTicketEntity::toDomain).toList();
    }

    public LotteryTicket findLotteryTicketById(UUID id) {
        LotteryTicketEntity lotteryTicketEntity = lotteryTicketRepository.findByDomainId(id)
                .orElseThrow(NoSuchElementException::new);
        return lotteryTicketEntity.toDomain();
    }

    public void generateTickets(){
        List<PrizeEntity> prizeEntityList = prizeRepository.findAll();
        List<LotteryTicketEntity> lotteryTicketEntityList = lotteryTicketRepository.findAll();
        Integer ticketNumber = lotteryTicketEntityList.size() + 1;
        List<PrizeEntity> usedPrizeEntityList = lotteryTicketEntityList
                .stream()
                .map(LotteryTicketEntity::getPrize)
                .toList();
        prizeEntityList.removeAll(usedPrizeEntityList);

        List<LotteryTicketEntity> newLotteryTicketEntityList = new ArrayList<>();
        for (PrizeEntity prizeEntity : prizeEntityList) {
            newLotteryTicketEntityList.add(
                    new LotteryTicketEntity(
                            null, UUID.randomUUID(),ticketNumber.longValue(), prizeEntity.getCategory(), prizeEntity,false));
            ticketNumber++;
        }
        lotteryTicketRepository.saveAll(newLotteryTicketEntityList);
    }

}

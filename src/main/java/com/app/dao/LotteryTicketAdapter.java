package com.app.dao;

import com.app.dbmodel.LotteryTicketEntity;
import com.app.dbmodel.PrizeEntity;
import com.app.dbmodel.UserEntity;
import com.app.dbmodel.UserTicketEntity;
import com.app.model.LotteryTicket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LotteryTicketAdapter {
    private final LotteryTicketRepository lotteryTicketRepository;
    private final PrizeRepository prizeRepository;
    private final UserRepository userRepository;
    private final UserTicketRepository userTicketRepository;

    public List<LotteryTicket> getAll() {
        List<LotteryTicketEntity> lotteryTicketEntityList = lotteryTicketRepository.findAll();
        return lotteryTicketEntityList.stream().map(LotteryTicketEntity::toDomain).toList();
    }

    public LotteryTicket findLotteryTicketByUUID(UUID id) {
        LotteryTicketEntity lotteryTicketEntity = lotteryTicketRepository.findByDomainId(id)
                .orElseThrow(NoSuchElementException::new);
        return lotteryTicketEntity.toDomain();
    }

    public LotteryTicket findLotteryTicketByNumber(String number) {
        LotteryTicketEntity lotteryTicketEntity = lotteryTicketRepository.findByNumber(number)
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
                            null, UUID.randomUUID(),addOffset(ticketNumber), prizeEntity.getCategory(), prizeEntity,false, null));
            ticketNumber++;
        }
        lotteryTicketRepository.saveAll(newLotteryTicketEntityList);
    }


    public void setIsUsed(UUID uuid, UUID volunteerUuid){
        lotteryTicketRepository.setIsUsed(uuid, volunteerUuid);
    }

    public Boolean isAssigned(UUID uuid){
        Optional<LotteryTicketEntity> foundTicket = userRepository.findAll()
                .stream()
                .map(UserEntity::getLotteryTickets)
                .flatMap(List::stream)
                .filter(ticket -> Objects.equals(uuid,ticket.getDomainId()))
                .findFirst();
        return foundTicket.isPresent();
    }

    public UUID setWinner(UUID uuid) {
        Optional<UserTicketEntity> userTicketEntity = userTicketRepository.findByTicketId(uuid);
        if(userTicketEntity.isPresent()){
            Optional<UserEntity> userEntity = userRepository.findByDomainId(userTicketEntity.get().getUserId());
            if(userEntity.isPresent()){
                userRepository.clearWinner();
                userRepository.setWinner(userEntity.get().getDomainId());
                return userEntity.get().getDomainId();
            }
        }
        return null;
    }

    private String addOffset(Integer ticketNumber){
        String finalNumber = ticketNumber.toString();
        while(finalNumber.length() < 4){
            finalNumber = "0" + finalNumber;
        }
        return finalNumber;
    }
}

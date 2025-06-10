package com.app.service;

import com.app.dao.LotteryTicketAdapter;
import com.app.model.LotteryTicket;
import com.app.service.utility.PdfGenerator;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LotteryTicketService {
    private final LotteryTicketAdapter lotteryTicketAdapter;

    public List<LotteryTicket> getAllTickets() {
        return lotteryTicketAdapter.getAll();
    }

    public LotteryTicket getTicketById(UUID id) {
        return lotteryTicketAdapter.findLotteryTicketByUUID(id);
    }

    public LotteryTicket getTicketByNumber(String number) {
        return lotteryTicketAdapter.findLotteryTicketByNumber(number);
    }

    public void generateTickets() {
        lotteryTicketAdapter.generateTickets();
    }

    public byte[] generateQRCodes(){
        List<LotteryTicket> ticketList = this.getAllTickets();
        try {
            return PdfGenerator.generatePdfWithQrCodes(ticketList, "testQR");
        } catch (IOException | WriterException e) {
            throw new RuntimeException(e);
        }

    }

    public void setisUsed(UUID uuid, UUID volunteerUuid){
        lotteryTicketAdapter.setIsUsed(uuid, volunteerUuid);
    }

    public Boolean isAssigned(UUID uuid){
        return lotteryTicketAdapter.isAssigned(uuid);
    }

    public UUID setWinner(UUID uuid) {
        return lotteryTicketAdapter.setWinner(uuid);
    }
}

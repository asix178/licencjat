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
        return lotteryTicketAdapter.findLotteryTicketById(id);
    }

    public void generateTickets() {
        lotteryTicketAdapter.generateTickets();
    }

    public void generateQRCodes(){
        List<String> stringList = this.getAllTickets().stream().map(ticket -> ticket.getId().toString()).toList();
        try {
            PdfGenerator.generatePdfWithQrCodes(stringList, "testQR");
        } catch (IOException | WriterException e) {
            throw new RuntimeException(e);
        }
    }
}

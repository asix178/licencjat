package com.app.controller;

import com.app.service.LotteryTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("ticket")
public class RestLotteryTicketController {
    private final LotteryTicketService lotteryTicketService;

    @GetMapping
    public ResponseEntity<?> getAllTickets() {
        return ResponseEntity.ok(lotteryTicketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable UUID id) {
        return ResponseEntity.ok(lotteryTicketService.getTicketById(id));
    }

    @PostMapping
    public ResponseEntity<?> generateTickets(){
        lotteryTicketService.generateTickets();
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/QR")
    public ResponseEntity<?> generateTicketsQR(){
        lotteryTicketService.generateQRCodes();
        return ResponseEntity.created(null).build();
    }
}

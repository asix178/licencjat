package com.app.controller;

import com.app.service.LotteryTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("ticket")
@CrossOrigin(origins="*")
public class RestLotteryTicketController {
    private final LotteryTicketService lotteryTicketService;

    @GetMapping
    public ResponseEntity<?> getAllTickets() {
        return ResponseEntity.ok(lotteryTicketService.getAllTickets());
    }

    @GetMapping("/uuid/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable UUID id) {
        return ResponseEntity.ok(lotteryTicketService.getTicketById(id));
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<?> getTicketById(@PathVariable Long number) {
        return ResponseEntity.ok(lotteryTicketService.getTicketByNumber(number));
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

    @GetMapping("/generate")
    public ResponseEntity<?> generate(){
        lotteryTicketService.generateTickets();
        return ResponseEntity.ok(Base64.getEncoder().encodeToString(lotteryTicketService.generateQRCodes()));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllTickets() {
        lotteryTicketService.deleteAllTickets();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/isUsed/{uuid}")
    public ResponseEntity<?> setIsUsed(@PathVariable UUID uuid){
        lotteryTicketService.setisUsed(uuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/isAssigned/{uuid}")
    public ResponseEntity<?> isAssigned(@PathVariable UUID uuid){
        return ResponseEntity.ok(lotteryTicketService.isAssigned(uuid));
    }
}

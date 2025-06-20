package com.app.controller;

import com.app.controller.response.WinnerMessage;
import com.app.service.LotteryTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("ticket")
@CrossOrigin(origins="*")
public class RestLotteryTicketController {
    private final LotteryTicketService lotteryTicketService;


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping
    public ResponseEntity<?> getAllTickets() {
        return ResponseEntity.ok(lotteryTicketService.getAllTickets());
    }

    @GetMapping("/uuid/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable UUID id) {
        return ResponseEntity.ok(lotteryTicketService.getTicketById(id));
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<?> getTicketById(@PathVariable String number) {
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


    @PutMapping("/isUsed/{uuid}/volunteer/{volunteerUuid}")
    public ResponseEntity<?> setIsUsed(@PathVariable UUID uuid, @PathVariable UUID volunteerUuid){
        lotteryTicketService.setisUsed(uuid, volunteerUuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/isAssigned/{uuid}")
    public ResponseEntity<?> isAssigned(@PathVariable UUID uuid){
        return ResponseEntity.ok(lotteryTicketService.isAssigned(uuid));
    }

    @PutMapping("/winner/{uuid}")
    public ResponseEntity<?> setWinner(@PathVariable UUID uuid){
        UUID userId = lotteryTicketService.setWinner(uuid);
        String ticketNumber = lotteryTicketService.getTicketById(uuid).getNumber();
        messagingTemplate.convertAndSend("/topic/winner", new WinnerMessage(userId, ticketNumber));
        return ResponseEntity.noContent().build();
    }
}

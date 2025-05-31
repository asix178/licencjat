package com.app.controller;

import com.app.controller.request.PrizeRequest;
import com.app.service.PrizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("prize")
@CrossOrigin(origins="*")
public class RestPrizeController {
    private final PrizeService prizeService;
    @PostMapping
    public ResponseEntity<?> addAllPrizes(@RequestBody PrizeRequest prizeRequest){
        prizeService.addAllPrizes(prizeRequest);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<?> getAllPrizes() {
        return ResponseEntity.ok(prizeService.getAllPrizes());
    }

    @GetMapping("/count")
    public ResponseEntity<?> countPrizes() {
        return ResponseEntity.ok(prizeService.countPrizesByName());
    }
}

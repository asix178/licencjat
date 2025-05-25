package com.app.controller;

import com.app.controller.request.PrizeRequest;
import com.app.service.PrizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("prize")
public class RestPrizeController {
    private final PrizeService prizeService;
    @PostMapping
    public ResponseEntity<?> addAllPrizes(@RequestBody PrizeRequest prizeRequest){
        prizeService.addAllPrizes(prizeRequest);
        return ResponseEntity.ok().build();
    }
}

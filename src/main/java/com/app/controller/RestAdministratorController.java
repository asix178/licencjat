package com.app.controller;

import com.app.controller.request.AdministratorRequest;
import com.app.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping ("administrator")
@CrossOrigin(origins="*")
public class RestAdministratorController {
private final AdministratorService administratorService;
@PostMapping
public ResponseEntity<?> register(@RequestBody AdministratorRequest administratorRequest) {
    try{
        Boolean result = administratorService.register(administratorRequest);
        if(result){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().body(false);
    }catch (Exception e){
    return ResponseEntity.badRequest().body(e.getMessage());
    }
}
@PutMapping
public ResponseEntity<?> login(@RequestBody AdministratorRequest administratorRequest) {
    Boolean result = administratorService.login(administratorRequest);
    if(result){
        return ResponseEntity.ok(true);
    }
    return ResponseEntity.badRequest().body(false);
}
}

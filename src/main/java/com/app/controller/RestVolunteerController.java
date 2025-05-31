package com.app.controller;

import com.app.controller.request.VolunteerRequest;
import com.app.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("volunteer")
@CrossOrigin(origins="*")
public class RestVolunteerController {
    private final VolunteerService volunteerService;
    @PostMapping
    public ResponseEntity<?> register(@RequestBody VolunteerRequest volunteerRequest) {
        try{
            Boolean result = volunteerService.register(volunteerRequest);
            if(result){
                return ResponseEntity.ok(true);
            }
            return ResponseEntity.badRequest().body(false);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<?> login(@RequestBody VolunteerRequest volunteerRequest) {
        Boolean result = volunteerService.login(volunteerRequest);
        if(result){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().body(false);
    }

}

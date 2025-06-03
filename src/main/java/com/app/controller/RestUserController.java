package com.app.controller;

import com.app.controller.request.AddTicketRequest;
import com.app.controller.request.AdministratorRequest;
import com.app.controller.request.UserRequest;
import com.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("uzytkownik")
@CrossOrigin(origins="*")
public class RestUserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest) {
        try {
            Boolean result = userService.register(userRequest);
            if (result) {

                return ResponseEntity.ok(true);
            }
            return ResponseEntity.badRequest().body(false);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {
        String result = userService.login(userRequest);
        if (Objects.nonNull(result)) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("addTicket")
    public ResponseEntity<?> addTicket(@RequestBody AddTicketRequest addTicketRequest) {
        userService.addTicket(addTicketRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getUser(@PathVariable UUID uuid) {
        return ResponseEntity.ok(userService.findUserByDomainId(uuid));
    }
}

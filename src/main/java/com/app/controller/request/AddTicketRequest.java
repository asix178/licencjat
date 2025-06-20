package com.app.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class AddTicketRequest {
    private UUID userId;
    private UUID ticketId;
}

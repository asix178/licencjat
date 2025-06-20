package com.app.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class PrizeRequest {
    private final String name;
    private final UUID categoryId;
    private final int amount;
}

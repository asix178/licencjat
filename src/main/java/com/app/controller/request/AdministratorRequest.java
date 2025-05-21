package com.app.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdministratorRequest {
    private final String login;
    private final String password;
}

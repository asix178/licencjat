package com.app.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VolunteerRequest {
    private final String name;
    private final String password;
}

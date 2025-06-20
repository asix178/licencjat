package com.app.service;

import com.app.controller.request.AdministratorRequest;
import com.app.dao.AdministratorAdapter;
import com.app.model.Administrator;
import com.app.security.Bcrypt;
import com.password4j.Password;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdministratorService {
    private final AdministratorAdapter administratorAdapter;

    public String login(AdministratorRequest administratorRequest) {
        try {
            Administrator administrator = administratorAdapter.findAdministratorByLogin(administratorRequest.getLogin());
            if (Password.check(administratorRequest.getPassword(), administrator.getPassword())
                    .with(Bcrypt.getBcryptFunction())) {
                return Jwts.builder().claim("adminId", administrator.getId()).claim("role", "admin").compact();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public Boolean register(AdministratorRequest administratorRequest) {
        try {
            administratorAdapter.findAdministratorByLogin(administratorRequest.getLogin());
        } catch (NoSuchElementException e) {
            Administrator administrator = new Administrator(UUID.randomUUID(), administratorRequest.getLogin()
                    , Password.hash(administratorRequest.getPassword()).with(Bcrypt.getBcryptFunction()).getResult());
            administratorAdapter.register(administrator);
            return true;
        }
        return false;
    }
}

package com.app.service;

import com.app.controller.request.AddTicketRequest;
import com.app.controller.request.UserRequest;
import com.app.dao.UserAdapter;
import com.app.model.User;
import com.app.security.Bcrypt;
import com.password4j.Password;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserAdapter userAdapter;

    public String login(UserRequest userRequest) {
        try {
            User user = userAdapter.findUserByLogin(userRequest.getLogin());
            if(Password.check(userRequest.getPassword(), user.getPassword())
                    .with(Bcrypt.getBcryptFunction())){
                return Jwts.builder().claim("userId",user.getId()).claim("role","user").compact();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public Boolean register(UserRequest userRequest) {
        try {
            userAdapter.findUserByLogin(userRequest.getLogin());
        } catch (NoSuchElementException e) {
            User user = new User(UUID.randomUUID(), userRequest.getLogin()
                    , Password.hash(userRequest.getPassword()).with(Bcrypt.getBcryptFunction()).getResult(),null);
            userAdapter.register(user);
            return true;
        }
        return false;
    }

    public void addTicket(AddTicketRequest addTicketRequest){
        userAdapter.addTicket(addTicketRequest.getUserId(),addTicketRequest.getTicketId());
    }

    public User findUserByDomainId(UUID domainId){
        return userAdapter.findUserById(domainId);
    }

}

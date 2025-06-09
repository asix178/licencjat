package com.app.dao;

import com.app.dbmodel.LotteryTicketEntity;
import com.app.dbmodel.UserEntity;
import com.app.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAdapter {
    private final UserRepository userRepository;
    private final LotteryTicketRepository lotteryTicketRepository;
    public User findUserByLogin(String login) {
        Optional<UserEntity> userFetched = userRepository.findByLogin(login);
        if (userFetched.isPresent()) {
            return userFetched.get().toDomain();
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<User> findAllUsers() {
        return userRepository.findAll().stream().map(UserEntity::toDomain).toList();
    }

    public void register(User user) {
        userRepository.save(UserEntity.fromDomain(user));
    }

    public void addTicket(UUID userId, UUID ticketId) {
        Optional<UserEntity> userFetched = userRepository.findByDomainId(userId);
        Optional<LotteryTicketEntity>  ticketFetched = lotteryTicketRepository.findByDomainId(ticketId);
        if (userFetched.isPresent() && ticketFetched.isPresent()) {
            userFetched.get().getLotteryTickets().add(ticketFetched.get());
            userRepository.save(userFetched.get());
        } else {
            throw new NoSuchElementException();
        }
    }

    public User findUserById(UUID id) {
        Optional<UserEntity> userFetched = userRepository.findByDomainId(id);
        return userFetched.get().toDomain();
    }


}

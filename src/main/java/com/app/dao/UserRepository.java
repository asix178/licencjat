package com.app.dao;

import com.app.dbmodel.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByLogin(String login);
    Optional<UserEntity> findByDomainId(UUID domainId);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity ue SET ue.isWinner = false ")
    void clearWinner();

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity ue SET ue.isWinner = true WHERE ue.domainId = :domainId")
    void setWinner(UUID domainId);
}

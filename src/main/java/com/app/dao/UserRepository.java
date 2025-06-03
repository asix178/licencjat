package com.app.dao;

import com.app.dbmodel.AdministratorEntity;
import com.app.dbmodel.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByLogin(String login);
    Optional<UserEntity> findByDomainId(UUID domainId);
}

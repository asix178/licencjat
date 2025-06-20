package com.app.dao;

import com.app.dbmodel.AdministratorEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<AdministratorEntity,Long> {
    Optional<AdministratorEntity> findByLogin(String login);
}

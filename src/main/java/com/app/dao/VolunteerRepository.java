package com.app.dao;

import com.app.dbmodel.VolunteerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<VolunteerEntity,Long> {
    Optional<VolunteerEntity> findByName(String name);
}

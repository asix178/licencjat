package com.app.dao;

import com.app.dbmodel.VolunteerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<VolunteerEntity,Long> {
}

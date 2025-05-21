package com.app.dao;

import com.app.dbmodel.PrizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrizeRepository extends JpaRepository<PrizeEntity,Long> {
}

package com.wellBeing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellBeing.entity.HealthLog;

@Repository
public interface HealthLogRepository extends JpaRepository<HealthLog,Integer>{
	
}

package com.wellBeing.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellBeing.entity.HealthLog;
import com.wellBeing.repository.HealthLogRepository;

@Service
public class HealthLogService {
	
	@Autowired
	private HealthLogRepository healthLogRepo;
	
	public List<HealthLog> getHeath(){
		return healthLogRepo.findAll();	
	}
	
	public HealthLog saveHealthLog(HealthLog log) {
		log.setDate(LocalDateTime.now());
		return healthLogRepo.save(log);
	}
	
	public Optional<HealthLog> findById(int id) {
		return healthLogRepo.findById(id);
	}
	
	public void deleteHeathLog(Integer id) {
		healthLogRepo.deleteById(id);
	}

}

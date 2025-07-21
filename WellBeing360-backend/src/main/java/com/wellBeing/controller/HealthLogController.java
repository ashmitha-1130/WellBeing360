package com.wellBeing.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellBeing.entity.HealthLog;
import com.wellBeing.service.HealthLogService;

@RestController
@RequestMapping("/health")
public class HealthLogController {

	@Autowired
	private HealthLogService healthController;
	
	@GetMapping("/getData")
	public ResponseEntity<?> getAllHeathRecord(){
		List<HealthLog> all = healthController.getHeath();
		if(all != null & !all.isEmpty()) {
			return new ResponseEntity<>(all,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/addData")
	public ResponseEntity<HealthLog> addHealthLog(@RequestBody HealthLog log){
		HealthLog logdata = healthController.saveHealthLog(log);
		return new ResponseEntity<>(logdata,HttpStatus.CREATED);
	}
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getHeathById(@PathVariable int id){
		Optional<HealthLog> dataById = healthController.findById(id);
		return new ResponseEntity<>(dataById,HttpStatus.OK);
	}
	
	@PutMapping("/updateData/{id}")
	public ResponseEntity<?> updateHealth(@RequestBody HealthLog log, @PathVariable int id ){
	    Optional<HealthLog> data = healthController.findById(id);
	    if(data.isPresent()) {
	        HealthLog existing = data.get();
	        existing.setSleep_hours(log.getSleep_hours());
	        existing.setMood_rating(log.getMood_rating());
//	        existing.setUser(log.getUser());

	        HealthLog updated = healthController.saveHealthLog(existing);
	        return new ResponseEntity<>(updated, HttpStatus.OK);
	    }
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id){
		healthController.deleteHeathLog(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
}

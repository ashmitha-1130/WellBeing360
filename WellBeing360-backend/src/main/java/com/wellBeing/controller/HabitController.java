package com.wellBeing.controller;

import java.time.LocalDate;
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

import com.wellBeing.entity.Habit;
import com.wellBeing.entity.User;
import com.wellBeing.repository.HabitRepository;
import com.wellBeing.service.HabitService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("api/habits")
public class HabitController {
	
	@Autowired
	private HabitService habbitService;
	
	@Autowired
	private HabitRepository habitRepository;
	
	private User getCurrentUser(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(user == null) {
			throw new RuntimeException("user does not exists");
		}
		return user;
	}
	
	@GetMapping("/getHabit")
	public ResponseEntity<?> getHabit(HttpSession session){
		User user = getCurrentUser(session);
		return ResponseEntity.ok(habbitService.getHabbit(user));
	}
	
	@PostMapping("/addHabbit")
	public ResponseEntity<?> addHabit(@RequestBody Habit habit,HttpSession session) {
		User user = getCurrentUser(session);
		return ResponseEntity.ok(habbitService.addHabbit(user.getUsername(), habit));
	}
	
	@PutMapping("/{habit_id}/mark")
	public ResponseEntity<?> markAsDone(@PathVariable Long habit_id, HttpSession session){
		User user = getCurrentUser(session);
		Habit hab = habbitService.markHabitAsDone(habit_id, user);
		return new ResponseEntity<>(hab,HttpStatus.OK);
			
	}
	@GetMapping("/streak-summary")
	public ResponseEntity<?> getStreakSummary(HttpSession session){
		User user = getCurrentUser(session);
		Habit hab = habbitService.getTopStreakHabit(user);
		return new ResponseEntity<>(hab,HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{habit_id}")
	public ResponseEntity<?> deleteHabit(@PathVariable Long habit_id,HttpSession session){
		User user = getCurrentUser(session);
		habbitService.deleteHabit(habit_id, user);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Habit deleted successfully");
	}

}

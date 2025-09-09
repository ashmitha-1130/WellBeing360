package com.wellBeing.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellBeing.entity.Habit;
import com.wellBeing.entity.User;
import com.wellBeing.repository.HabitRepository;
import com.wellBeing.repository.UserRepository;

@Service
public class HabitService {
	
	@Autowired
	private HabitRepository habbitRepository;
	
	@Autowired
	private UserRepository userReposistory;
	
	public List<Habit> getHabbit(User user){
		return habbitRepository.findByUser(user);	
	}
	
	public Habit addHabbit(String username,Habit habit) {
		User user = userReposistory.findByUsername(username);
		if(user == null) {
			throw new RuntimeException("User does not exists");
		}
		habit.setUser(user);
		return habbitRepository.save(habit);
	}
	
	  public Habit markHabitAsDone(Long habitId,User user) {
	        Habit habit = habbitRepository.findById(habitId)
	                .orElseThrow(() -> new RuntimeException("Habit not found"));
	        
	        if(!habit.getUser().getUser_id().equals(user.getUser_id())) {
	        		throw new RuntimeException("Unauthorized");
	        }

	        LocalDate today = LocalDate.now();
	        boolean shouldIncrement = false;
	
	 if (habit.getFrequency() == Habit.Frequency.Daily) {
         if (habit.getLastCompletedDate() == null ||
             !habit.getLastCompletedDate().isEqual(today)) {
             shouldIncrement = true;
         }
     } else if (habit.getFrequency() == Habit.Frequency.Weekly) {
         LocalDate last = habit.getLastCompletedDate();
         if (last == null || last.plusWeeks(1).isBefore(today)) {
             shouldIncrement = true;
         }
     }

     if (shouldIncrement) {
         habit.setStreakCount(habit.getStreakCount() + 1);
         habit.setLastCompletedDate(today);
     }

     return habbitRepository.save(habit);
 }

 public Habit getTopStreakHabit(User user) {
     List<Habit> habits = habbitRepository.findByUser(user);
     return habits.stream()
             .max((h1, h2) -> Integer.compare(h1.getStreakCount(), h2.getStreakCount()))
             .orElse(null);
 }
 

 		public void deleteHabit(Long id,User user) {
 			Habit habit = habbitRepository.findById(id)
 					.orElseThrow(() ->  new RuntimeException("Habit does not exists"));
 			
 			if(!habit.getUser().getUser_id().equals(user.getUser_id())) {
 				throw new RuntimeException("User unauthorized");
 			}
 			habbitRepository.delete(habit);
 		}

}


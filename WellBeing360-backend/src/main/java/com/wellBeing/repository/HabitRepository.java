package com.wellBeing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellBeing.entity.Habit;
import com.wellBeing.entity.User;

@Repository
public interface HabitRepository extends JpaRepository<Habit,Long> {
	
	List<Habit> findByUser(User user);

}

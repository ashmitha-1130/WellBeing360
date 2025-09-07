package com.wellBeing.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wellBeing.entity.HealthLog;
import com.wellBeing.entity.User;

@Repository
public interface HealthLogRepository extends JpaRepository<HealthLog,Long>{
	
	   // Find a log for a user on a specific date
    Optional<HealthLog> findByUserAndDate(User user, LocalDate date);

    // Find all logs for a user
    List<HealthLog> findByUser(User user);

    // Find logs within a date range (useful for weekly summaries)
    List<HealthLog> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);

    // Example: Get average sleep and mood in a given range (weekly summary)
    @Query("SELECT AVG(h.sleep_hours), AVG(h.mood_rating) " +
           "FROM HealthLog h WHERE h.user = :user AND h.date BETWEEN :startDate AND :endDate")
    Object getWeeklySummary(User user, LocalDate startDate, LocalDate endDate);
	
}

package com.wellBeing.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellBeing.entity.WeeklySummaryDto;
import com.wellBeing.entity.HealthLog;
import com.wellBeing.entity.User;
import com.wellBeing.repository.HealthLogRepository;

import jakarta.validation.Valid;

@Service
public class HealthLogService {

    @Autowired
    private HealthLogRepository healthLogRepo;

    /**
     * Create or update today's log for a user
     */
    public HealthLog createOrUpdateLog(@Valid User user, HealthLog logRequest) {
        LocalDate today = LocalDate.now();

        // Check if a log already exists for today
        Optional<HealthLog> existingLog = healthLogRepo.findByUserAndDate(user, today);

        HealthLog log = existingLog.orElse(new HealthLog());
        log.setUser(user);
        log.setDate(today);
        log.setSleep_hours(logRequest.getSleep_hours());
        log.setWater_ml(logRequest.getWater_ml());
        log.setMood_rating(logRequest.getMood_rating());
//        log.setNotes(logRequest.getNotes());

        return healthLogRepo.save(log);
    }

    /**
     * Get all logs for a user
     */
    public List<HealthLog> getLogs(User user) {
        return healthLogRepo.findByUser(user);
    }

    /**
     * Get weekly summary (last 7 days)
     */
    public WeeklySummaryDto getWeeklySummary(User user) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minus(6, ChronoUnit.DAYS);

        List<HealthLog> logs = healthLogRepo.findByUserAndDateBetween(user, startDate, endDate);

        double avgSleep = logs.stream()
                .mapToInt(HealthLog::getSleep_hours)
                .average()
                .orElse(0.0);

        double avgMood = logs.stream()
                .mapToInt(HealthLog::getMood_rating)
                .average()
                .orElse(0.0);

        return new WeeklySummaryDto(avgSleep, avgMood, logs.size());
    }

    /**
     * Delete log by date for a user
     */
    public boolean deleteLogByDate(User user, LocalDate date) {
        Optional<HealthLog> log = healthLogRepo.findByUserAndDate(user, date);
        if (log.isPresent()) {
            healthLogRepo.delete(log.get());
            return true;
        }
        return false;
    }
}

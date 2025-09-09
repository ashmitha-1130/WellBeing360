package com.wellBeing.controller;

import com.wellBeing.entity.WeeklySummaryDto;
import com.wellBeing.entity.HealthLog;
import com.wellBeing.entity.User;
import com.wellBeing.service.HealthLogService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/health")
public class HealthLogController {

    @Autowired
    private HealthLogService healthLogService;

   
     // Get current user from session
     
    private User getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("User not logged in");
        }
        return user;
    }

    
     // Create or update today's log
     
    @PostMapping("/addLog")
    public ResponseEntity<?> createOrUpdateLog(@RequestBody HealthLog log, HttpSession session) {
        User user = getCurrentUser(session);
        if(log.getMood_rating() > 5) {
        		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mood rating should be in range of (1-5)");
        }
        HealthLog saved = healthLogService.createOrUpdateLog(user, log);
        
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    
      // Get all logs for the current user
    
    @GetMapping("/getLogs")
    public ResponseEntity<?> getAllLogs(HttpSession session) {
        User user = getCurrentUser(session);
        List<HealthLog> logs = healthLogService.getLogs(user);

        if (logs.isEmpty()) {
            return new ResponseEntity<>("No logs found", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    
     // Get weekly summary (last 7 days)
     
    @GetMapping("/weekly-summary")
    public ResponseEntity<WeeklySummaryDto> getWeeklySummary(HttpSession session) {
        User user = getCurrentUser(session);
        WeeklySummaryDto summary = healthLogService.getWeeklySummary(user);
        return ResponseEntity.ok(summary);
    }

   
     // Delete log for a given date
     
    @DeleteMapping("/delete/{date}")
    public ResponseEntity<?> deleteLog(@PathVariable String date, HttpSession session) {
        User user = getCurrentUser(session);
        LocalDate parsedDate = LocalDate.parse(date);

        boolean deleted = healthLogService.deleteLogByDate(user, parsedDate);
        if (deleted) {
            return ResponseEntity.ok("Log deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No log found for this date");
        }
    }
}

package com.wellBeing.entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeeklySummaryDto {
    private double avgSleepHours;
    private double avgMoodRating;
    private int totalLogs;
}
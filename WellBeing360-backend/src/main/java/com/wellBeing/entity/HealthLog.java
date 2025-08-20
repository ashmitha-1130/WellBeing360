package com.wellBeing.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class HealthLog {

    public enum MoodRating {
        VERY_SAD,
        SAD,
        NEUTRAL,
        HAPPY,
        VERY_HAPPY
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int health_id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer sleep_hours;
    
    @Column(nullable = false)
    private Integer water_ml;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MoodRating mood_rating;
    
    private String notes;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
}

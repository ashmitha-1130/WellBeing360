package com.wellBeing.entity;

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
    private int id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Integer sleep_hours;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MoodRating mood_rating;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
}

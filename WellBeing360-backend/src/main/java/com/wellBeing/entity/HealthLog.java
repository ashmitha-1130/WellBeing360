package com.wellBeing.entity;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
    name = "health_log",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "date"})
    }
)
public class HealthLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long health_id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer sleep_hours;

    @Column(nullable = false)
    private Integer water_ml;

    /**
     * Mood rating: stored as integer (1–5)
     * 1 = VERY_SAD, 2 = SAD, 3 = NEUTRAL, 4 = HAPPY, 5 = VERY_HAPPY
     */
    @Column(nullable = false)
    @Min(1)
    @Max(5)
    private Integer mood_rating;

//    private String notes;

    // -------- Simple Enum for readability --------
    public enum MoodRating {
        VERY_SAD,   // 1
        SAD,        // 2
        NEUTRAL,    // 3
        HAPPY,      // 4
        VERY_HAPPY  // 5
    }
}

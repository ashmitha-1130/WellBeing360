package com.wellBeing.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
		name="habit",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"user_id","name"})
		}
		
		)
public class Habit {
	
	public enum Frequency{
		Daily,
		Weekly
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long habitId;
    @Column(nullable = false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name="user_id",nullable=false)
    private User user;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable =false)
    private Frequency frequency;
    
    @Column(name = "streak_count")
    private int streakCount;

    @Column(name = "last_completed_date")
    private LocalDate lastCompletedDate;
    
   
 
}

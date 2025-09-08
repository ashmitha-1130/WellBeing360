package com.wellBeing.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
		name="habbit",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"user_id","name"})
		}
		
		)
public class Habbit {
	
	public enum Frequency{
		Daily,
		Weekly
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long habbit_id;
    @Column(nullable = false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name="user_id",nullable=false)
    
    @Enumerated(EnumType.STRING)
    @Column(nullable =false)
    private Frequency frequency;
    
    @Column(nullable = false)
    private int streak_count;
    private LocalDate last_completed_date;
 
}

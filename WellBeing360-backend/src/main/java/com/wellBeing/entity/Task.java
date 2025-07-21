package com.wellBeing.entity;

import jakarta.persistence.*;

@Entity
public class Task {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
}

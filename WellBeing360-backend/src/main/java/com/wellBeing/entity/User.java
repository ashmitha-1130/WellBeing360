package com.wellBeing.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(
    name = "users",
    indexes = {
        @Index(name = "idx_username", columnList = "username", unique = true)
    }
)
public class User {
<<<<<<< HEAD
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	private String userName;
	private String password;
	private List<String> roles;
	@OneToMany
	private List<HealthLog> healthlog;
=======
>>>>>>> branch 'main' of https://github.com/ashmitha-1130/WellBeing360.git

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private LocalDateTime createdAt = LocalDateTime.now();

}
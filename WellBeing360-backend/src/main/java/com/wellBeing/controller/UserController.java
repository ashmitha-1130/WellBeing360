package com.wellBeing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellBeing.entity.User;
import com.wellBeing.repository.UserRepository;
//import com.wellBeing.entity;
import com.wellBeing.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired UserRepository userRepository;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user){
		if(userRepository.findByUsername(user.getUsername())!=null) {
			return ResponseEntity.badRequest().body("User already exists");
		}
		userService.saveUser(user);
		return ResponseEntity.ok("User Registered!");
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User users,HttpSession session){
		if(userService.validateUser(users.getUsername(), users.getPassword())) {
			User user = userRepository.findByUsername(users.getUsername());
			session.setAttribute("user", user);
			return ResponseEntity.ok("Login successfull");
		}
		
		return ResponseEntity.badRequest().body("Invalid username or password");
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session){
		session.invalidate();
		return ResponseEntity.ok("Logged out");
	}	
	
	@GetMapping("/user")
	public ResponseEntity<?> getUser(HttpSession session) {
	    User user = (User) session.getAttribute("user");
	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
	    }
	    return ResponseEntity.ok(user);
	}

	@PutMapping("/{username}")
	public ResponseEntity<?> updateUser(@PathVariable String username,@RequestBody User users, HttpSession session){
		User user = (User)session.getAttribute("user");
		if(user == null) {
			return ResponseEntity.badRequest().body("User not logged in");
		}
		userService.updateUser(user.getUsername(), users);
		return ResponseEntity.ok("User updated");		
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<?> deleteUser(@PathVariable String username,HttpSession session){
		User user = userRepository.findByUsername(username);
		if(user == null) {
			return ResponseEntity.badRequest().body("User does not exists");
		}
		userService.deleteUser(username);
		session.invalidate();
		return ResponseEntity.ok("User deleted");
		
	}

}

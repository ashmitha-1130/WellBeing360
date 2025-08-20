package com.wellBeing.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellBeing.entity.User;
import com.wellBeing.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getAll")
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		return userService.saveNewUser(user);
	}
	
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}
	
	@PutMapping("update/{id}")
	public void updateUser(@PathVariable int id, @RequestBody User user) {
		 userService.saveUser(user, id);
	
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUserById(id);
	}

}

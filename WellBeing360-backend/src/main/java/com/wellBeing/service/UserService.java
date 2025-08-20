package com.wellBeing.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellBeing.entity.User;
import com.wellBeing.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}

	public User saveNewUser(User u) {
		u.setRoles(Arrays.asList("USER"));
		return userRepository.save(u);	
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUserName(username);
	}
	
	public Optional<User> getUserById(int id) {
		return userRepository.findById(id);
	}
	public void saveUser(User u, int id) {
		Optional<User> u1 = getUserById(id);
		if(u1.isPresent()) {
			User us = u1.get();
			us.setUserName(u.getUserName());
			us.setPassword(u.getPassword());
			us.setRoles(Arrays.asList("USER"));
			 userRepository.save(us);
		}
		
	}
	
	
	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}

}

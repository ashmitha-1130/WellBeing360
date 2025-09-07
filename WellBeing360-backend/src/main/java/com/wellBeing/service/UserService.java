package com.wellBeing.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wellBeing.entity.User;
import com.wellBeing.repository.UserRepository;


@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User saveUser( User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User updateUser(String username , User user) {
		User user1 = userRepository.findByUsername(username);
		if(user1 == null) {
			throw new RuntimeException("User does not exisis");
		}
		user1.setUsername(user.getUsername());
		user1.setEmail(user.getEmail());
		user1.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user1);	
		
	}

	public void deleteUser(String username) {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new RuntimeException("User does not exist");
		}
		userRepository.delete(user);
		
	}
	
	public boolean validateUser(String username, String password) {
		User user = userRepository.findByUsername(username);
		return user!=null && passwordEncoder.matches(password,user.getPassword());
		
	}
	
}

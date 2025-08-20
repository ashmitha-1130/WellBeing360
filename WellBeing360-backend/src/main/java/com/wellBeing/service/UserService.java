package com.wellBeing.service;


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
	
	public User saveUser( User user) {
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(int id) {
		Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
	}

	public User updateUser(int id, User user) {
		User existingUser = getUserById(id);
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
	}

	public void deleteUser(int id) {
		userRepository.deleteById(id);
		
	}
	
	

	
}

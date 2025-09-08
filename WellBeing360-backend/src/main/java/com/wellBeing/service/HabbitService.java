package com.wellBeing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellBeing.entity.Habbit;
import com.wellBeing.entity.User;
import com.wellBeing.repository.HabbitRepository;
import com.wellBeing.repository.UserRepository;

@Service
public class HabbitService {
	
	@Autowired
	private HabbitRepository habbitRepository;
	
	@Autowired
	private UserRepository userReposistory;
	
	public List<Habbit> getHabbit(String username){
		User user = userReposistory.findByUsername(username);
		if(user == null) {
			throw new RuntimeException("User does not exists");
		}
		return habbitRepository.findAll();
	}

	
	public Habbit addHabbit(String username,Habbit habit) {
		User user = userReposistory.findByUsername(username);
		if(user == null) {
			throw new RuntimeException("User does not exists");
		}
		return habbitRepository.save(habit);
	}
	

}

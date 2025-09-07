package com.wellBeing.entity;

import lombok.Data;

@Data
public class UserDto {
	
	private long user_id;
	private String username;
	private String password;
	private String email;
	
	public User toEntity() {
		User user = new User();
		user.setUser_id(this.user_id);
		user.setUsername(this.username);
		user.setEmail(this.email);
		user.setPassword(this.password);
		
		return  user;
		
	}
	public UserDto(User user) {
		this.user_id = user.getUser_id();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.password = user.getPassword();
	}
	

}

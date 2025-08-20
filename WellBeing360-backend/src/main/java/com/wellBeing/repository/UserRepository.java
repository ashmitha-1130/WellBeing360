package com.wellBeing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellBeing.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
<<<<<<< HEAD
	
     User findByUserName(String UserName);
     
     User deleteByUserName(String UserName);
	
	

=======
	User findByUsername(String username);
>>>>>>> branch 'main' of https://github.com/ashmitha-1130/WellBeing360.git
}

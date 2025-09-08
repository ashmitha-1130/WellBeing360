package com.wellBeing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellBeing.entity.Habbit;

@Repository
public interface HabbitRepository extends JpaRepository<Habbit,Long> {
	
	

}

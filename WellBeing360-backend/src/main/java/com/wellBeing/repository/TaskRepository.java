package com.wellBeing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellBeing.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer>{

}

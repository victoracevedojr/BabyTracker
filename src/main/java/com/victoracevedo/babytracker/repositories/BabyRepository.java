package com.victoracevedo.babytracker.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.victoracevedo.babytracker.models.Baby;

public interface BabyRepository  extends CrudRepository<Baby, Long> {
	List<Baby> findAll();
	
	void deleteById(Long id);
}

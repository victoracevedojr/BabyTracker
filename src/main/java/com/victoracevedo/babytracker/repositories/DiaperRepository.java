package com.victoracevedo.babytracker.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.victoracevedo.babytracker.models.Diaper;

public interface DiaperRepository extends CrudRepository<Diaper, Long> {
	List<Diaper> findAll();
	
	void deleteById(Long id);
}

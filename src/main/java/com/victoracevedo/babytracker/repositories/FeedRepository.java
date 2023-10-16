package com.victoracevedo.babytracker.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.victoracevedo.babytracker.models.Feed;

public interface FeedRepository extends CrudRepository<Feed, Long> {
	List<Feed> findAll();
	
	void deleteById(Long id);
}

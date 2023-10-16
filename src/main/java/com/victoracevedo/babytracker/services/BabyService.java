package com.victoracevedo.babytracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victoracevedo.babytracker.models.Baby;
import com.victoracevedo.babytracker.models.Diaper;
import com.victoracevedo.babytracker.models.Feed;
import com.victoracevedo.babytracker.repositories.BabyRepository;
import com.victoracevedo.babytracker.repositories.DiaperRepository;
import com.victoracevedo.babytracker.repositories.FeedRepository;

@Service
public class BabyService {
	
	@Autowired
	private BabyRepository babyRepo;
	@Autowired
	private FeedRepository feedRepo;
	@Autowired
	private DiaperRepository diaperRepo;
	
	// Create
	public Baby createBaby(Baby baby) {
		return babyRepo.save(baby);
	}
	
	// Read
	public Baby findBabyById(Long id) {
		Optional<Baby> optionalBaby = babyRepo.findById(id);
		return optionalBaby.isPresent() ? optionalBaby.get() : null;
	}
	public List<Baby> allBabies(){
		return babyRepo.findAll();
	}
	
	// Update
	public Baby updateBaby(Baby baby) {
		return babyRepo.save(baby);
	}
	
	// Delete
	public void deleteBaby(Long id) {
		Optional<Baby> thisBaby = babyRepo.findById(id);
		if(thisBaby.isPresent()) {
			Baby babyToDelete = thisBaby.get();
			for(Feed thisFeed: babyToDelete.getFeeds()) {
				thisFeed.setBaby(null);
				feedRepo.save(thisFeed);
			}
			for(Diaper thisDiaper: babyToDelete.getDiapers()) {
				thisDiaper.setBaby(null);
				diaperRepo.save(thisDiaper);
			}
			babyRepo.deleteById(id);
		}
	}
	
	
}

package com.victoracevedo.babytracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victoracevedo.babytracker.models.Feed;
import com.victoracevedo.babytracker.repositories.FeedRepository;

@Service
public class FeedService {
	
	@Autowired
	private FeedRepository feedRepo;
	
	// Create
	public Feed createFeed(Feed newFeed) {
		return feedRepo.save(newFeed);
	}
	
	// Read
	public Feed findFeedById(Long id) {
		Optional<Feed> optionalFeed = feedRepo.findById(id);
		return optionalFeed.isPresent() ? optionalFeed.get() : null;
	}
	public List<Feed> allFeeds(){
		return feedRepo.findAll();
	}
	
	// Update
	public Feed updateFeed(Feed feed) {
		return feedRepo.save(feed);
	}
	
	// Delete 
	public void deleteFeed(Long id) {
		Optional<Feed> thisFeed = feedRepo.findById(id);
		if(thisFeed.isPresent()) {
			Feed feedToDelete = thisFeed.get();
			feedToDelete.setBaby(null);
			feedRepo.save(feedToDelete);
			feedRepo.deleteById(id);
		}
	}
}

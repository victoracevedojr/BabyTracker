package com.victoracevedo.babytracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victoracevedo.babytracker.models.Diaper;
import com.victoracevedo.babytracker.repositories.DiaperRepository;

@Service
public class DiaperService {
	
	@Autowired
	private DiaperRepository diaperRepo;
	
	// Create
	public Diaper createDiaper(Diaper newDiaper) {
		return diaperRepo.save(newDiaper);
	}
	
	// Read
	public Diaper findDiaperById(Long id) {
		Optional<Diaper> optionalDiaper = diaperRepo.findById(id);
		return optionalDiaper.isPresent() ? optionalDiaper.get() : null;
	}
	public List<Diaper> allDiapers(){
		return diaperRepo.findAll();
	}
	
	// Update
	public Diaper updateDiaper(Diaper diaper) {
		return diaperRepo.save(diaper);
	}
	
	// Delete 
	public void deleteDiaper(Long id) {
		Optional<Diaper> thisDiaper = diaperRepo.findById(id);
		if(thisDiaper.isPresent()) {
			Diaper diaperToDelete = thisDiaper.get();
			diaperToDelete.setBaby(null);
			diaperRepo.save(diaperToDelete);
			diaperRepo.deleteById(id);
		}
	}
}

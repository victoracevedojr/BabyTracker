package com.victoracevedo.babytracker.storage;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface StorageService {

	void init();
	
	void store(MultipartFile file);
	
	Set<String> loadAll();
	
}

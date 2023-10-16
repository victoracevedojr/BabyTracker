package com.victoracevedo.babytracker.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemUploadService implements StorageService{

	private final Path rootLocation;
	
	public FileSystemUploadService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}
	@Override
	public void init() {
		if(!Files.exists(rootLocation)) {
			try {
				Files.createDirectory(rootLocation);
			} catch(IOException e) {
				throw new StorageException("Could not initialize storage", e);
			}
		}

	}

	@Override
	public void store(MultipartFile file) {
		try {
			if(file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
		}
		
	}

	@Override
	public Set<String> loadAll() {
		return Stream.of(new File(rootLocation.toString()).listFiles())
				.filter(file->!file.isDirectory())
				.map(File::getName)
				.collect(Collectors.toSet());
	}
	
//	@Override
//	public Path load(String filename) {
//		return rootLocation.resolve(filename);
//	}

}

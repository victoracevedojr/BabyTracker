package com.victoracevedo.babytracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.victoracevedo.babytracker.storage.FileSystemUploadService;
import com.victoracevedo.babytracker.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class BabyTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BabyTrackerApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(FileSystemUploadService storageService) {
		return (args) -> {
			storageService.init();
			};
	}

}

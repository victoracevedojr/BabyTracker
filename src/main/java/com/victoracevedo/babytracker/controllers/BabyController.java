package com.victoracevedo.babytracker.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.victoracevedo.babytracker.models.Baby;
import com.victoracevedo.babytracker.models.User;
import com.victoracevedo.babytracker.services.BabyService;
import com.victoracevedo.babytracker.services.DiaperService;
import com.victoracevedo.babytracker.services.FeedService;
import com.victoracevedo.babytracker.services.UserService;
//import com.victoracevedo.babytracker.storage.FileSystemUploadService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/babies")
public class BabyController {
	@Autowired
	BabyService babyServ;
	@Autowired
	UserService userServ;
	@Autowired
	FeedService feedServ;
	@Autowired
	DiaperService diaperServ;
//	@Autowired
//	FileSystemUploadService storageService;
	
	// Methods used for creating a new baby
	@GetMapping("/new")
	public String newBaby(@ModelAttribute("baby") Baby baby, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "niceTry.jsp";
		} else {
			User user = userServ.findById(userId);
			model.addAttribute("user", user);
			LocalDate today = LocalDate.now();
			model.addAttribute("today", today);
			return "newBaby.jsp";
		}
	}
	@PostMapping("/new")
	public String createBaby(
			@Valid @ModelAttribute("baby") Baby baby,
			BindingResult result, Model model) {
		if(result.hasErrors()) {
			LocalDate today = LocalDate.now();
			model.addAttribute("today", today);
			return "newBaby.jsp";
		} else {
			// Save the baby's data to the database
//			Photo Upload to be implemented
//			if(!photo.isEmpty()) {
//				
//				storageService.store(photo);				
//			}
			babyServ.createBaby(baby);
				
			return "redirect:/home";
		}
	}
	
	//Method to view a single baby
	@GetMapping("/{id}")
	public String details(@PathVariable("id") Long id, Model model, HttpSession session) {
		Baby baby = babyServ.findBabyById(id);
		model.addAttribute("baby", baby);
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "niceTry.jsp";
		} else {
			User user = userServ.findById(userId);
			model.addAttribute("user", user);
			LocalDate today = LocalDate.now();
			model.addAttribute("today", today);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy"); 
			model.addAttribute("formatter", formatter);
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("h:mm a, M/d/yy"); 
			model.addAttribute("formatter2", formatter2);
			return "viewBaby.jsp";
		}
	}
	
	// Methods to update baby
	@GetMapping("/{babyId}/edit")
	public String edit(@PathVariable("babyId") Long babyId, Model model, HttpSession session) {
		Baby babyToUpdate = babyServ.findBabyById(babyId);
		if(session.getAttribute("userId").equals(babyToUpdate.getUser().getId())) {
			model.addAttribute("babyToUpdate", babyToUpdate);
			return "editBaby.jsp";
		} else {
			return "niceTry.jsp";
		}
	}
	
	@PutMapping("/{babyId}/edit")
	public String update(@PathVariable("babyId") Long babyId, @Valid @ModelAttribute("babyToUpdate") Baby babyToUpdate, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			Baby thisBaby = babyServ.findBabyById(babyId);
			model.addAttribute("thisBaby", thisBaby);
			return "editBaby.jsp";
		} else if(session.getAttribute("userId").equals(babyToUpdate.getUser().getId())) {
			Baby existingBaby = babyServ.findBabyById(babyId);
			existingBaby.setFirstName(babyToUpdate.getFirstName());
			existingBaby.setLastName(babyToUpdate.getLastName());
			existingBaby.setGender(babyToUpdate.getGender());
			existingBaby.setBirthday(babyToUpdate.getBirthday());
			babyServ.updateBaby(existingBaby);
			return "redirect:/home";
		} else {
			return "niceTry.jsp";
		}
	}
	
	// Method to delete a baby
	@DeleteMapping("/{babyId}/delete")
	public String destroyBaby(@PathVariable("babyId") Long babyId, HttpSession session) {
		Baby babyToDelete = babyServ.findBabyById(babyId);
		if(session.getAttribute("userId").equals(babyToDelete.getUser().getId())) {
			System.out.println("Baby removed successfully");
			babyServ.deleteBaby(babyId);
			return "redirect:/home";
		} else {
			System.out.println("User not authorized to remove baby");
			return "niceTry.jsp";			
		}
	}	
	
	@GetMapping("/resources")
	public String resources(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		// If no user is longed in, redirect to home page
		if(userId==null) {
			return "niceTry.jsp";
		}
		User user = userServ.findById(userId);
		model.addAttribute("user", user);
		return "resources.jsp";
	}
}

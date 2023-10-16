package com.victoracevedo.babytracker.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.victoracevedo.babytracker.models.Baby;
import com.victoracevedo.babytracker.models.Diaper;
import com.victoracevedo.babytracker.services.BabyService;
import com.victoracevedo.babytracker.services.DiaperService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class DiaperController {
	@Autowired
	BabyService babyServ;
	@Autowired
	DiaperService diaperServ;
	
	@GetMapping("/babies/{babyId}/diaper/new")
	public String newDiaper(@PathVariable("babyId") Long babyId, @ModelAttribute("newDiaper") Diaper newDiaper, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "niceTry.jsp";
		} else {
			Baby baby = babyServ.findBabyById(babyId);
			model.addAttribute("baby", baby);
			return "newDiaper.jsp";
		}
	}
	
	@PostMapping("/babies/{babyId}/diaper/new")
	public String createDiaper(@Valid @ModelAttribute("newDiaper") Diaper newDiaper, BindingResult result, @RequestParam("baby") Long baby) {
		if(result.hasErrors()) {
			result.addAllErrors(result);
			return "newDiaper.jsp";
		} else {
			diaperServ.createDiaper(newDiaper);
			return "redirect:/babies/" + baby;
		}
	}
	
	@GetMapping("/babies/{babyId}/diaper/{diaperId}/edit")
	public String editDiaper(@PathVariable("babyId") Long babyId, @PathVariable("diaperId") Long diaperId, Model model, HttpSession session) {
		Diaper diaperToUpdate = diaperServ.findDiaperById(diaperId);
		if(session.getAttribute("userId").equals(diaperToUpdate.getBaby().getUser().getId())) {
			model.addAttribute("diaperToUpdate", diaperToUpdate);
			Baby baby = babyServ.findBabyById(babyId);
			model.addAttribute("baby", baby);
			return "editDiaper.jsp";
		} else {
			return "niceTry.jsp";
		}
	}
	
	@PutMapping("/babies/{babyId}/diaper/{diaperId}/edit")
	public String updateDiaper(@PathVariable("babyId") Long babyId, @PathVariable("diaperId") Long diaperId, @Valid @ModelAttribute("diaperToUpdate") Diaper diaperToUpdate, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			Baby baby = babyServ.findBabyById(babyId);
			model.addAttribute("baby", baby);
			Diaper thisDiaper = diaperServ.findDiaperById(diaperId);
			model.addAttribute("thisDiaper", thisDiaper);
			return "editDiaper.jsp";
		} else if(session.getAttribute("userId").equals(diaperToUpdate.getBaby().getUser().getId())) {
			Diaper existingDiaper = diaperServ.findDiaperById(diaperId);
			existingDiaper.setTime(diaperToUpdate.getTime());
			existingDiaper.setDiaperType(diaperToUpdate.getDiaperType());
			existingDiaper.setNotes(diaperToUpdate.getNotes());
			diaperServ.updateDiaper(existingDiaper);
			return "redirect:/babies/" + existingDiaper.getBaby().getId();
		} else {
			return "niceTry.jsp";
		}
	}
	
	@DeleteMapping("/babies/{babyId}/diaper/{diaperId}/delete")
	public String destroyDiaper(@PathVariable("babyId") Long babyId, @PathVariable("diaperId") Long diaperId, HttpSession session) {
		Diaper diaperToDelete = diaperServ.findDiaperById(diaperId);
		if(session.getAttribute("userId").equals(diaperToDelete.getBaby().getUser().getId())) {
			System.out.println("Diaper removed successfully");
			diaperServ.deleteDiaper(diaperId);
			return "redirect:/babies/" + babyId;
		} else {
			System.out.println("User not authorized to remove diaper");
			return "niceTry.jsp";			
		}
	}
}

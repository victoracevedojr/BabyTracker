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
import com.victoracevedo.babytracker.models.Feed;
import com.victoracevedo.babytracker.services.BabyService;
import com.victoracevedo.babytracker.services.FeedService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class FeedController {
	@Autowired
	BabyService babyServ;
	@Autowired
	FeedService feedServ;
	
	@GetMapping("/babies/{babyId}/feed/new")
	public String newFeed(@PathVariable("babyId") Long babyId, @ModelAttribute("newFeed") Feed newFeed, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "niceTry.jsp";
		} else {
			Baby baby = babyServ.findBabyById(babyId);
			model.addAttribute("baby", baby);
			return "newFeed.jsp";
		}
	}
	
	@PostMapping("/babies/{babyId}/feed/new")
	public String createFeed(@Valid @ModelAttribute("newFeed") Feed newFeed, BindingResult result, @RequestParam("baby") Long baby) {
		if(result.hasErrors()) {
			result.addAllErrors(result);
			return "newFeed.jsp";
		} else {
			feedServ.createFeed(newFeed);
			return "redirect:/babies/" + baby;
		}
	}
	
	@GetMapping("/babies/{babyId}/feed/{feedId}/edit")
	public String editFeed(@PathVariable("babyId") Long babyId, @PathVariable("feedId") Long feedId, Model model, HttpSession session) {
		Feed feedToUpdate = feedServ.findFeedById(feedId);
		if(session.getAttribute("userId").equals(feedToUpdate.getBaby().getUser().getId())) {
			model.addAttribute("feedToUpdate", feedToUpdate);
			Baby baby = babyServ.findBabyById(babyId);
			model.addAttribute("baby", baby);
			return "editFeed.jsp";
		} else {
			return "niceTry.jsp";
		}
	}
	
	@PutMapping("/babies/{babyId}/feed/{feedId}/edit")
	public String updateFeed(@PathVariable("babyId") Long babyId, @PathVariable("feedId") Long feedId, @Valid @ModelAttribute("feedToUpdate") Feed feedToUpdate, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			Feed thisFeed = feedServ.findFeedById(feedId);
			model.addAttribute("thisFeed", thisFeed);
			Baby baby = babyServ.findBabyById(babyId);
			model.addAttribute("baby", baby);
			return "editFeed.jsp";
		} else if(session.getAttribute("userId").equals(feedToUpdate.getBaby().getUser().getId())) {
			Feed existingFeed = feedServ.findFeedById(feedId);
			existingFeed.setTime(feedToUpdate.getTime());
			existingFeed.setAmount(feedToUpdate.getAmount());
			existingFeed.setFeedType(feedToUpdate.getFeedType());
			existingFeed.setNotes(feedToUpdate.getNotes());
			feedServ.updateFeed(existingFeed);
			return "redirect:/babies/" + existingFeed.getBaby().getId();
		} else {
			return "niceTry.jsp";
		}
	}
	
	@DeleteMapping("/babies/{babyId}/feed/{feedId}/delete")
	public String destroyFeed(@PathVariable("babyId") Long babyId, @PathVariable("feedId") Long feedId, HttpSession session) {
		Feed feedToDelete = feedServ.findFeedById(feedId);
		if(session.getAttribute("userId").equals(feedToDelete.getBaby().getUser().getId())) {
			System.out.println("Feed removed successfully");
			feedServ.deleteFeed(feedId);
			return "redirect:/babies/" + babyId;
		} else {
			System.out.println("User not authorized to remove feed");
			return "niceTry.jsp";			
		}
	}
}

package com.victoracevedo.babytracker.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.victoracevedo.babytracker.models.LoginUser;
import com.victoracevedo.babytracker.models.User;
import com.victoracevedo.babytracker.services.BabyService;
import com.victoracevedo.babytracker.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	private UserService userServ;
	@Autowired
	BabyService babyServ;
	
//	@Autowired
//	FileSystemUploadService storageService;
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		// When returning to home, session userId is set to null automatically. Essentially user is logged out if they return to default route
		session.setAttribute("userId", null);
		// Bind empty User and LoginUser objects to the JSP to capture the form input
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "loginReg.jsp";
	}
	// Form routes on home page
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		// Call a register method in the service to do some extra validations and create a new user!
		userServ.register(newUser, result);
		if(result.hasErrors()) {
			// Send in the empty LoginUser before re-rendering the page.
			model.addAttribute("newLogin", new LoginUser());
			return "loginReg.jsp";
		}
		// No errors! Store their ID from the DB in session, in other words, log them in.
		session.setAttribute("userId", newUser.getId());
		return "redirect:/home";
	}
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
		User user = userServ.login(newLogin, result);
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "loginReg.jsp";
		}
		// No errors! Store their ID from the DB in session, in other words, log them in.
		session.setAttribute("userId", user.getId());
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String dashboard(Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		// If no user is longed in, redirect to home page
		if(userId==null) {
			return "niceTry.jsp";
		}
		User user = userServ.findById(userId);
		model.addAttribute("user", user);
		LocalDate today = LocalDate.now();
		model.addAttribute("today", today);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy"); 
		model.addAttribute("formatter", formatter);
//		Set<String> filenames = storageService.loadAll();
//		model.addAttribute("images", filenames);
		return "home.jsp";
	}
	
	@PostMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.setAttribute("userId", null);
		return "redirect:/";
	}
	
}

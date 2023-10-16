package com.victoracevedo.babytracker.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.victoracevedo.babytracker.models.LoginUser;
import com.victoracevedo.babytracker.models.User;
import com.victoracevedo.babytracker.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	// This method will be called from the controller whenever a user submits a registration form.
    public User register(User newUser, BindingResult result) {
    	// Checks for email in database
    	Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
    	
    	// Potential Blocks:
    	// Reject if email is taken (present in database)
    	if(potentialUser.isPresent()) {result.rejectValue("email", "Matches", "Email is already being used");}
        // Reject if password doesn't match confirmation
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");}
        // Return null if result has errors
    	if(result.hasErrors()) {return null;}
       
    	// Hash and set password, save user to database
    	String hashedPw = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashedPw);
        return userRepo.save(newUser);
    }

    
    // This method will be called from the controller whenever a user submits a login form.
    public User login(LoginUser newLoginUser, BindingResult result) {
        
    	// Find potential user in the DB by email
    	Optional<User> potentialUser = userRepo.findByEmail(newLoginUser.getEmail());

        // Reject if NOT present
    	if(!potentialUser.isPresent()) {
    		result.rejectValue("email", "Matches", "User email not found");
    		return null;
    	}
        
        // Reject if BCrypt password match fails
    	User user = potentialUser.get();
    	if(!BCrypt.checkpw(newLoginUser.getPassword(), user.getPassword())) {
    		result.rejectValue("password", "Matches", "Incorrect Password!");
    	}
    
        // Return null if result has errors
    	if(result.hasErrors()) {
    	    // Exit the method and go back to the controller to handle the response
    	    return null;
    	}
        // Otherwise, return the user object
    	return user;
    }

    public User findById(Long id) {
    	Optional<User> potentialUser = userRepo.findById(id);
    	if(potentialUser.isPresent()) {
    		return potentialUser.get();
    	}
    	return null;
    }
    
    // Allows updates to be made to user (i.e. relationships to babies)
    public User updateUser(User user) {
    	return userRepo.save(user);
    }
}

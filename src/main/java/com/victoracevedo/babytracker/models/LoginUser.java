package com.victoracevedo.babytracker.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginUser {
    @NotEmpty(message="Please enter an email")
    @Email(message="Please enter a valid email!")  
    private String email;
    
    @NotEmpty(message="Password is required!")
    @Size(min=8, message="Password must be at least 8 characters long")
    private String password;
    
    public LoginUser() {}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

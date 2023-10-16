package com.victoracevedo.babytracker.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(min=2, message="Name must be at least 2 characters long")
    @Pattern(regexp = "^[A-Za-z]*$", message="Must contain only letters!")
    private String firstName;
    
    @Size(min=2, message="Name must be at least 2 characters long")
    @Pattern(regexp = "^[A-Za-z]*$", message="Must contain only letters!")
    private String lastName;
    
    @NotEmpty(message="Please enter an email")
    @Email(message="Please enter a valid email!")   
    private String email;
    
    @NotEmpty(message="Password is required!")
    @Pattern(regexp="^.{8,}$", message="Password must be at least 8 characters long")
    private String password;
    
    @Transient
    @NotEmpty(message="Confirm Password is required!")
    private String confirm;
  
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @OneToMany(mappedBy="user", fetch=FetchType.LAZY)
    private List<Baby> babies;
    
    
    @PrePersist
    protected void onCreate(){
    	this.createdAt = new Date();
    	this.updatedAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
    	this.updatedAt = new Date();
    }
    
    public User() {}
	public User(String firstName, String lastName, String email, String password, String confirm, List<Baby> babies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
		this.babies = babies;
	}
	public Long getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getConfirm() {
		return confirm;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public List<Baby> getBabies() {
		return babies;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setBabies(List<Baby> babies) {
		this.babies = babies;
	}
    

}

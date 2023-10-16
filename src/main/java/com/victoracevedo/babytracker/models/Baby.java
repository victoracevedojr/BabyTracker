package com.victoracevedo.babytracker.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="babies")
public class Baby {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(min=2, message="Must be at least 2 characters")
    @Pattern(regexp = "^[A-Za-z]*$", message="Must contain only letters!")
    private String firstName;
    
    @Size(min=2, message="Must be at least 2 characters")
    @Pattern(regexp = "^[A-Za-z]*$", message="Must contain only letters!")
    private String lastName;
    
    @NotEmpty(message="Please make a selection")
    private String gender;
    
    private LocalDate birthday;
    private byte[] photo;
    
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    @OneToMany(mappedBy="baby", fetch=FetchType.LAZY)
    private List<Feed> feeds;
    
    @OneToMany(mappedBy="baby", fetch=FetchType.LAZY)
    private List<Diaper> diapers;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    public Baby() {}
	public Baby(String firstName, String lastName, String gender, byte[] photo, LocalDate birthday, User user, List<Feed> feeds, List<Diaper> diapers) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.photo = photo;
		this.birthday = birthday;
		this.user = user;
		this.feeds = feeds;
		this.diapers = diapers;
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
	public String getGender() {
		return gender;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public User getUser() {
		return user;
	}
	public List<Feed> getFeeds() {
		return feeds;
	}
	public List<Diaper> getDiapers() {
		return diapers;
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
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setFeeds(List<Feed> feeds) {
		this.feeds = feeds;
	}
	public void setDiapers(List<Diaper> diapers) {
		this.diapers = diapers;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
    
    
}

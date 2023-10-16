package com.victoracevedo.babytracker.models;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="feeds")
public class Feed {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@NotNull(message="Please enter a time")
	private LocalDateTime time;
	
	@NotNull(message="Please enter an amount")
	@Min(value=0, message="Must be a positive amount")
	private Double amount;
	
	@NotEmpty(message="Please make a selection")
    private String feedType;
	
	@Size(max=50, message="Notes must be under 50 characters")
    private String notes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="baby_id")
    private Baby baby;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @PrePersist
    protected void onCreate(){
    	this.createdAt = new Date();
    	this.updatedAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
    	this.updatedAt = new Date();
    }
    public Feed() {}
	public Feed(LocalDateTime time, Double amount, String feedType, String notes, Baby baby) {
		this.time = time;
		this.amount = amount;
		this.feedType = feedType;
		this.notes = notes;
		this.baby = baby;
	}
	public Long getId() {
		return id;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public Double getAmount() {
		return amount;
	}
	public String getFeedType() {
		return feedType;
	}
	public String getNotes() {
		return notes;
	}
	public Baby getBaby() {
		return baby;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public void setBaby(Baby baby) {
		this.baby = baby;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
    
}

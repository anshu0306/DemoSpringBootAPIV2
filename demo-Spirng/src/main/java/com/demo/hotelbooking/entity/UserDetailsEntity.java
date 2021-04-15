package com.demo.hotelbooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="userdetails")
@Entity(name="userdetails")
public class UserDetailsEntity {
	
	@Id
	@Column(name = "userid")
	private String userId;
	
	@Column(name = "userpoints")
	private Integer userPoints;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getUserPoints() {
		return userPoints;
	}
	public void setUserPoints(Integer userPoints) {
		this.userPoints = userPoints;
	}
	
	

}

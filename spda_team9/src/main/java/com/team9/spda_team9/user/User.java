package com.team9.spda_team9.user;

import java.util.List;

public class User {
//	private String userId; //if we cannot restrict username to unique will need this userId
	private String fullName;
    private String username;
    private String email;
    private Gender gender;
    private String location;
    private String token;
    private boolean suspended = false;
    
    private String selfDescription;
    private List<String> interest;
    private List<String> profession;
    private int numberOfKids;
    private String kidsDescription;
    
    private List<String> friends; //list of userId

    public User(){    }

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public String getSelfDescription() {
		return selfDescription;
	}

	public void setSelfDescription(String selfDescription) {
		this.selfDescription = selfDescription;
	}

	public List<String> getInterest() {
		return interest;
	}

	public void setInterest(List<String> interest) {
		this.interest = interest;
	}

	public List<String> getProfession() {
		return profession;
	}

	public void setProfession(List<String> profession) {
		this.profession = profession;
	}

	public int getNumberOfKids() {
		return numberOfKids;
	}

	public void setNumberOfKids(int numberOfKids) {
		this.numberOfKids = numberOfKids;
	}

	public String getKidsDescription() {
		return kidsDescription;
	}

	public void setKidsDescription(String kidsDescription) {
		this.kidsDescription = kidsDescription;
	}

	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
    
}

package com.movieApp.springboot;

public class GetUserModel {
	private String status;
	private UsersModel userModels;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public UsersModel getUserModels() {
		return userModels;
	}
	public void setUserModels(UsersModel userModels) {
		this.userModels = userModels;
	}
	
	
}

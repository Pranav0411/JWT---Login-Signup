package com.usecase.payload;

public class JwtAuthResponse {
	
	private String token;
	
	private StudentDataTransfer user;

	public StudentDataTransfer getUser() {
		return user;
	}

	public void setUser(StudentDataTransfer user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

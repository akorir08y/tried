package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Authentication{

	@JsonProperty("password")
	private String password;

	@JsonProperty("userName")
	private String userName;

	public Authentication() {
	}

	public Authentication(String password, String userName) {
		this.password = password;
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
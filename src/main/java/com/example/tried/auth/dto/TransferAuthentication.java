package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TransferAuthentication {

	@JsonProperty("password")
	private String password;

	@JsonProperty("userName")
	private String userName;

	public TransferAuthentication() {
	}

	public TransferAuthentication(String userName, String password) {
		this.userName = userName;
		this.password = password;
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
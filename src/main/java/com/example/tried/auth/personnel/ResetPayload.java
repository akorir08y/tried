package com.example.tried.auth.personnel;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ResetPayload {

	@JsonProperty("newPassword")
	private String newPassword;

	@JsonProperty("userName")
	private String userName;

	public ResetPayload() {
	}

	public ResetPayload(String newPassword, String userName) {
		this.newPassword = newPassword;
		this.userName = userName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
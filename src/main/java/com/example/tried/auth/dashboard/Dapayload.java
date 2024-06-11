package com.example.tried.auth.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Dapayload{

	@JsonProperty("password")
	private String password;

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("user")
	private String user;

	public Dapayload() {
	}

	public Dapayload(String password, String churchName, String churchCode, String user) {
		this.password = password;
		this.churchName = churchName;
		this.churchCode = churchCode;
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getChurchName() {
		return churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
	}

	public String getChurchCode() {
		return churchCode;
	}

	public void setChurchCode(String churchCode) {
		this.churchCode = churchCode;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
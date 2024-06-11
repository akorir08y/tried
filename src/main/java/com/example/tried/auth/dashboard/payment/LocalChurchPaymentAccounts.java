package com.example.tried.auth.dashboard.payment;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LocalChurchPaymentAccounts{

	@JsonProperty("password")
	private String password;

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("sessionNumber")
	private Long sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("user")
	private String user;

	public LocalChurchPaymentAccounts() {
	}

	public LocalChurchPaymentAccounts(String password, String churchName, Long sessionNumber, String function, String churchCode, String user) {
		this.password = password;
		this.churchName = churchName;
		this.sessionNumber = sessionNumber;
		this.function = function;
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

	public Long getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Long sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
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
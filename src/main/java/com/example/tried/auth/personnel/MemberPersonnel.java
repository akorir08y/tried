package com.example.tried.auth.personnel;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MemberPersonnel{

	@JsonProperty("password")
	private String password;

	@JsonProperty("function")
	private String function;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("connectionPurpose")
	private String connectionPurpose;

	@JsonProperty("user")
	private String user;

	public MemberPersonnel() {
	}

	public MemberPersonnel(String password, String function, String churchCode, String connectionPurpose, String user) {
		this.password = password;
		this.function = function;
		this.churchCode = churchCode;
		this.connectionPurpose = connectionPurpose;
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getConnectionPurpose() {
		return connectionPurpose;
	}

	public void setConnectionPurpose(String connectionPurpose) {
		this.connectionPurpose = connectionPurpose;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
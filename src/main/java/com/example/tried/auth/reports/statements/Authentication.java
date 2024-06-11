package com.example.tried.auth.reports.statements;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Authentication{

	@JsonProperty("personnelName")
	private String personnelName;

	@JsonProperty("password")
	private String password;

	@JsonProperty("institutionNumber")
	private String institutionNumber;

	@JsonProperty("institutionLevel")
	private String institutionLevel;

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("pin")
	private String pin;

	@JsonProperty("institutionName")
	private String institutionName;

	@JsonProperty("user")
	private String user;

	public Authentication() {

	}

	public Authentication(String personnelName, String password, String institutionNumber, String institutionLevel, String phoneNumber, String sessionNumber, String pin, String institutionName, String user) {
		this.personnelName = personnelName;
		this.password = password;
		this.institutionNumber = institutionNumber;
		this.institutionLevel = institutionLevel;
		this.phoneNumber = phoneNumber;
		this.sessionNumber = sessionNumber;
		this.pin = pin;
		this.institutionName = institutionName;
		this.user = user;
	}

	public String getPersonnelName() {
		return personnelName;
	}

	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInstitutionNumber() {
		return institutionNumber;
	}

	public void setInstitutionNumber(String institutionNumber) {
		this.institutionNumber = institutionNumber;
	}

	public String getInstitutionLevel() {
		return institutionLevel;
	}

	public void setInstitutionLevel(String institutionLevel) {
		this.institutionLevel = institutionLevel;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(String sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
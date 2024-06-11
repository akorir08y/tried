package com.example.tried.auth.member.specific;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Authentication{

	@JsonProperty("instututionName")
	private String institutionName;

	@JsonProperty("personnelName")
	private String personnelName;

	@JsonProperty("password")
	private String password;

	@JsonProperty("instututionLevel")
	private String institutionLevel;

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("sessionNumber")
	private Long sessionNumber;

	@JsonProperty("pin")
	private String pin;

	@JsonProperty("instututionNumber")
	private String institutionNumber;

	@JsonProperty("user")
	private String user;

	public Authentication() {
	}

	public Authentication(String institutionName, String personnelName, String password, String institutionLevel, String phoneNumber, Long sessionNumber, String pin, String institutionNumber, String user) {
		this.institutionName = institutionName;
		this.personnelName = personnelName;
		this.password = password;
		this.institutionLevel = institutionLevel;
		this.phoneNumber = phoneNumber;
		this.sessionNumber = sessionNumber;
		this.pin = pin;
		this.institutionNumber = institutionNumber;
		this.user = user;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
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

	public Long getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Long sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getInstitutionNumber() {
		return institutionNumber;
	}

	public void setInstitutionNumber(String institutionNumber) {
		this.institutionNumber = institutionNumber;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
package com.example.tried.auth.personnel.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Authentication{

	@JsonProperty("password")
	private String password;

	@JsonProperty("institutionName")
	private String institutionName;

	@JsonProperty("personnelName")
	private String personnelName;

	@JsonProperty("institutionLevel")
	private String institutionLevel;

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("institutionNumber")
	private String institutionNumber;

	@JsonProperty("user")
	private String user;

	public Authentication() {

	}

	public Authentication(String password, String institutionName, String personnelName, String institutionLevel, String sessionNumber, String institutionNumber, String user) {
		this.password = password;
		this.institutionName = institutionName;
		this.personnelName = personnelName;
		this.institutionLevel = institutionLevel;
		this.sessionNumber = sessionNumber;
		this.institutionNumber = institutionNumber;
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getInstitutionLevel() {
		return institutionLevel;
	}

	public void setInstitutionLevel(String institutionLevel) {
		this.institutionLevel = institutionLevel;
	}

	public String getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(String sessionNumber) {
		this.sessionNumber = sessionNumber;
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
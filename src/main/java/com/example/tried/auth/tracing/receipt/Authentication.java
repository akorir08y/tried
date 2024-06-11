package com.example.tried.auth.tracing.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Authentication{

	@JsonProperty("password")
	private String password;

	@JsonProperty("personnelName")
	private String personnelName;

	@JsonProperty("institutionNumber")
	private String institutionNumber;

	@JsonProperty("institutionLevel")
	private String institutionLevel;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("institutionName")
	private String institutionName;

	@JsonProperty("user")
	private String user;


	public Authentication() {

	}

	public Authentication(String password, String personnelName, String institutionNumber, String institutionLevel, Integer sessionNumber, String institutionName, String user) {
		this.password = password;
		this.personnelName = personnelName;
		this.institutionNumber = institutionNumber;
		this.institutionLevel = institutionLevel;
		this.sessionNumber = sessionNumber;
		this.institutionName = institutionName;
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPersonnelName() {
		return personnelName;
	}

	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
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

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
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
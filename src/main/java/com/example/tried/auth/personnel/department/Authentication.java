package com.example.tried.auth.personnel.department;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Authentication{

	@JsonProperty("password")
	private String password;

	@JsonProperty("instututionName")
	private String instututionName;

	@JsonProperty("personnelName")
	private String personnelName;

	@JsonProperty("instututionLevel")
	private String instututionLevel;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("instututionNumber")
	private String instututionNumber;

	@JsonProperty("user")
	private String user;

	public Authentication() {
	}

	public Authentication(String password, String instututionName, String personnelName, String instututionLevel, Integer sessionNumber, String instututionNumber, String user) {
		this.password = password;
		this.instututionName = instututionName;
		this.personnelName = personnelName;
		this.instututionLevel = instututionLevel;
		this.sessionNumber = sessionNumber;
		this.instututionNumber = instututionNumber;
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInstututionName() {
		return instututionName;
	}

	public void setInstututionName(String instututionName) {
		this.instututionName = instututionName;
	}

	public String getPersonnelName() {
		return personnelName;
	}

	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}

	public String getInstututionLevel() {
		return instututionLevel;
	}

	public void setInstututionLevel(String instututionLevel) {
		this.instututionLevel = instututionLevel;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getInstututionNumber() {
		return instututionNumber;
	}

	public void setInstututionNumber(String instututionNumber) {
		this.instututionNumber = instututionNumber;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
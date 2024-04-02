package com.example.tried.auth.personnel.receipting.local_church;

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

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("institutionName")
	private String institutionName;

	@JsonProperty("user")
	private String user;
}
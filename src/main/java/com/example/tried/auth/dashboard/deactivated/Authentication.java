package com.example.tried.auth.dashboard.deactivated;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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
	private Long sessionNumber;

	@JsonProperty("institutionName")
	private String institutionName;

	@JsonProperty("user")
	private String user;
}